package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StrategySerialize {

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            writeWithExeption(contacts.entrySet(), dos, element -> {
                dos.writeUTF(element.getKey().name());
                dos.writeUTF(element.getValue());
            });

            //implements sections
            Map<SectionType, Section> sections = resume.getSections();
            writeWithExeption(sections.entrySet(), dos, element -> {
                SectionType sectionType = element.getKey();
                dos.writeUTF(sectionType.name());
                Section section = element.getValue();
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        String content = ((TextSection) section).getContent();
                        dos.writeUTF(content);
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> list = ((ListSection) section).getItems();
                        writeWithExeption(list, dos, dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> listofOrganizations = ((OrganizationSection) section).getOrganizations();
                        writeWithExeption(listofOrganizations, dos, organization -> {
                            Link link = (organization).getHomepage();
                            dos.writeUTF(link.getName());
                            dos.writeUTF(link.getUrl());
                            List<Organization.PlaceDescription> placeDescriptions = (organization).getPlaceDescriptions();
                            writeWithExeption(placeDescriptions, dos, plDesc -> {
                                dos.writeUTF(unParseLocalDate(plDesc.getStartDate()));
                                dos.writeUTF(unParseLocalDate(plDesc.getEndDate()));
                                dos.writeUTF(plDesc.getTitle());
                                dos.writeUTF(plDesc.getDescription());
                            });

                        });
                        break;
                }
            });


        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readWithExeption(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            // implements sections
            readWithExeption(dis, () -> {
                SectionType type = SectionType.valueOf(dis.readUTF());
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.addSection(type, new TextSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> list = new ArrayList<>();
                        readWithExeption(dis, () -> list.add(dis.readUTF()));
                        resume.addSection(type, new ListSection(list));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> organizations = new ArrayList<>();
                        readWithExeption(dis, () -> {
                            List<Organization.PlaceDescription> placeDescriptions = new ArrayList<>();
                            String name = dis.readUTF();
                            String url = dis.readUTF();
                            Link link = new Link(name, url);
                            readWithExeption(dis, () -> {
                                LocalDate startDay = parseLocalDate(dis.readUTF());
                                LocalDate endDate = parseLocalDate(dis.readUTF());
                                String title = dis.readUTF();
                                String description = dis.readUTF();
                                placeDescriptions.add(new Organization.PlaceDescription(startDay, endDate, title, description));
                            });
                            organizations.add(new Organization(link, placeDescriptions));
                        });
                        OrganizationSection organizationSection = new OrganizationSection(organizations);
                        resume.addSection(type, organizationSection);
                        break;
                }
            });
            return resume;
        }
    }

    private interface Writer<Element> {
        void writeElement(Element element) throws IOException;
    }

    private interface Reader {
        void readElement() throws IOException;
    }

    private <Element> void writeWithExeption(Collection<Element> collection, DataOutputStream dos, Writer<Element> elementWriter) throws IOException {
        dos.writeInt(collection.size());
        for (Element element : collection) {
            elementWriter.writeElement(element);
        }
    }

    private void readWithExeption(DataInputStream dis, Reader elementReader) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            elementReader.readElement();

        }
    }

    private LocalDate parseLocalDate(String str) {
        return LocalDate.parse(str);
    }

    private String unParseLocalDate(LocalDate ld) {
        return ld.toString();
    }


}
