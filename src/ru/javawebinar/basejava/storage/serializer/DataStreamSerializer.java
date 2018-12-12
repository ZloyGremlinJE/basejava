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
            writeWithExeption(dos, contacts.entrySet(), element -> {
               dos.writeUTF(element.getKey().name());
               dos.writeUTF(element.getValue());
            });

            //implements sections

            Map<SectionType, Section> sections = resume.getSections();
            writeIntdata(dos, sections.size());
            sections.forEach((sectionType, section) -> {
                writeUTFdata(dos, sectionType.name(), false);
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        String content = ((TextSection) section).getContent();
                        writeUTFdata(dos, content, false);
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List list = ((ListSection) section).getItems();
                        writeIntdata(dos, list.size());
                        list.forEach(listSection -> writeUTFdata(dos, (String) listSection, false));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List listofOrganizations = ((OrganizationSection) section).getOrganizations();
                        writeIntdata(dos, listofOrganizations.size());
                        listofOrganizations.forEach(organization -> {
                            Link link = ((Organization) organization).getHomepage();
                            writeUTFdata(dos, link.getName(), false);
                            writeUTFdata(dos, link.getUrl(), true);
                            List<Organization.PlaceDescription> placeDescriptions = ((Organization) organization).getPlaceDescriptions();
                            writeIntdata(dos, placeDescriptions.size());
                            placeDescriptions.forEach(placeDescription -> {
                                writeUTFdata(dos, unparseLocalDate(placeDescription.getStartDate()), false);
                                writeUTFdata(dos, unparseLocalDate(placeDescription.getEndDate()), false);
                                writeUTFdata(dos, placeDescription.getTitle(), false);
                                writeUTFdata(dos, placeDescription.getDescription(), true);
                            });
                        });
                        break;
                }
            });
//            }

        }
    }

   private interface Writer <Element> {
        void writeElement(Element element) throws IOException;
    }

    private <Element> void  writeWithExeption(DataOutputStream dos, Collection<Element> collection, Writer<Element> write) throws IOException{
        dos.writeInt(collection.size());
        for(Element element : collection){
          write.writeElement(element);
        }
    }







    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            // implements sections
            int sizeOutputSection = dis.readInt();
            for (int k = 0; k < sizeOutputSection; k++) {
                String typeOf = dis.readUTF();
                SectionType type = SectionType.valueOf(typeOf);
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.addSection(type, new TextSection(dis.readUTF()));
                        break;

                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> list = new ArrayList<>();
                        int listsize = dis.readInt();
                        for (int i = 0; i < listsize; i++) {
                            String item = dis.readUTF();
                            list.add(item);
                        }
                        resume.addSection(type, new ListSection(list));
                        break;

                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> organizations = new ArrayList<>();
                        int listOrganizationSize = dis.readInt();

                        for (int i = 0; i < listOrganizationSize; i++) {
                            List<Organization.PlaceDescription> placeDescriptions = new ArrayList<>();
                            String name = dis.readUTF();
                            String url = dis.readUTF();
                            if (url.equals("")) {
                                url = null;
                            }
                            Link link = new Link(name, url);
                            int sizelistDescriptions = dis.readInt();

                            for (int j = 0; j < sizelistDescriptions; j++) {
                                LocalDate startDay = parseLocaleDate(dis.readUTF());
                                LocalDate endDate = parseLocaleDate(dis.readUTF());
                                String title = dis.readUTF();
                                String description = dis.readUTF();
                                if (description.equals("")) {
                                    description = null;
                                }
                                placeDescriptions.add(new Organization.PlaceDescription(startDay, endDate, title, description));
                            }
                            organizations.add(new Organization(link, placeDescriptions));
                        }

                        OrganizationSection organizationSection = new OrganizationSection(organizations);
                        resume.addSection(type, organizationSection);
                        break;
                }
            }


            return resume;
        }
    }

    private LocalDate parseLocaleDate(String s) {
        return LocalDate.parse(s);
    }

    private String unparseLocalDate(LocalDate localDate) {
        return localDate.toString();
    }

    private void writeUTFdata(DataOutputStream dos, String data, Boolean checkNull) {
        try {
            if (checkNull && data == null) {
                dos.writeUTF("");
            } else {
                dos.writeUTF(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void writeIntdata(DataOutputStream dos, Integer data) {
        try {
            dos.writeInt(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}