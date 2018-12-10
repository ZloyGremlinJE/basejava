package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StrategySerialize {

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
//            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
//                dos.writeUTF(entry.getKey().name());
//                dos.writeUTF(entry.getValue());
//            }
            contacts.forEach((key, value) -> {
                writeUTFdata(dos, key.name(), false);
                writeUTFdata(dos, value, false);
            });

            //implements sections

            Map<SectionType, Section> sections = resume.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                SectionType sectionType = entry.getKey();
                dos.writeUTF(entry.getKey().name());
                Section section = entry.getValue();
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        String content = ((TextSection) section).getContent();
                        dos.writeUTF(content);
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List list = ((ListSection) section).getItems();
                        int size = list.size();
                        dos.writeInt(size);
                        for (Object aList : list) {
                            dos.writeUTF((String) aList);
                        }
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List listofOrganizations = ((OrganizationSection) section).getOrganizations();
                        int sizeListOrganization = listofOrganizations.size();
                        dos.writeInt(sizeListOrganization);
                        listofOrganizations.forEach(s -> {
//                        for (Object listofOrganization : listofOrganizations) {
//                            Organization organization = (Organization) listofOrganization;
//                            Link link = organization.getHomepage();
//                            dos.writeUTF(link.getName());
//                            if (link.getUrl() != null) {
//                                dos.writeUTF(link.getUrl());
//                            } else {
//                                dos.writeUTF("");
//                            }
                            Link link = ((Organization) s).getHomepage();
                            writeUTFdata(dos, link.getName(), false);
                            writeUTFdata(dos, link.getUrl(), true);

                            List<Organization.PlaceDescription> placeDescriptions = ((Organization) s).getPlaceDescriptions();
                            int sizeListDescriptions = placeDescriptions.size();
                            writeIntdata(dos, sizeListDescriptions);
//                            for (Organization.PlaceDescription placeDescription : placeDescriptions) {
//                                dos.writeUTF(placeDescription.getStartDate().toString());
//                                dos.writeUTF(placeDescription.getEndDate().toString());
//                                dos.writeUTF(placeDescription.getTitle());
//                                if (placeDescription.getDescription() != null) {
//                                    dos.writeUTF(placeDescription.getDescription());
//                                } else {
//                                    dos.writeUTF("");
//                                }
//                            }
                            placeDescriptions.forEach(s1 -> {
                                writeUTFdata(dos, unparseLocalDate(s1.getStartDate()), false);
                                writeUTFdata(dos, unparseLocalDate(s1.getEndDate()), false);
                                writeUTFdata(dos, s1.getTitle(), false);
                                writeUTFdata(dos, s1.getDescription(), true);
                            });
                        });
                        break;
                }
            }

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
