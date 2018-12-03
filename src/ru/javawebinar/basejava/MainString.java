package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainString {

    protected static final File STORAGE_DIR = new File("D:\\basejava\\storage");


    public static void main(String[] args) {
//        String[] strArray = new String[]{"1", "2", "3", "4", "5"};
////        String result = "";
//        StringBuilder sb = new StringBuilder();
//        for (String str : strArray) {
//            sb.append(str).append(", ");
//        }
//        System.out.println(sb.toString());
//
//        String str1 = "abc";
//        String str3 = "c";
//        String str2 = ("ab" + str3).intern();
//        System.out.println(str1 == str2);

        //input
        Resume resumeOut = ResumeTestData.getResumeTestData();
        Map<ContactType, String> contacts = resumeOut.getContacts();
        Map<SectionType, Section> sections = resumeOut.getSections();

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("D:\\basejava\\storage\\resumeTest.bin"))) {

            dos.writeUTF(resumeOut.getUuid());
            dos.writeUTF(resumeOut.getFullName());


            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }


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
                        for (int i = 0; i < size; i++) {
                            dos.writeUTF((String) list.get(i));
                        }
                        break;

                    case EXPERIENCE:
                       list = ((OrganizationSection)section).getOrganizations();
                         size = list.size();
                        dos.writeInt(size);


                        break;
                    case EDUCATION:
                        break;
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }


        //output
        Resume resumeIn = null;
        try (DataInputStream dis = new DataInputStream(new FileInputStream("D:\\basejava\\storage\\resumeTest.bin"))) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            resumeIn = new Resume(uuid, fullName);
            int sizeOutputContacts = dis.readInt();
            for (int i = 0; i < sizeOutputContacts; i++) {
                resumeIn.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            int sizeOutputSection = dis.readInt();
            for (int k = 0; k < sizeOutputSection; k++) {
                SectionType type = SectionType.valueOf(dis.readUTF());
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resumeIn.addSection(type, new TextSection(dis.readUTF()));
                        break;

                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> list = new ArrayList<>();
                        int listsize = dis.readInt();
                        for (int i = 0; i < listsize; i++) {
                            String item = dis.readUTF();
                            list.add(item);
                        }
                        resumeIn.addSection(type, new ListSection(list));
                        break;

                    case EXPERIENCE:
                        List<Organization.PlaceDescription> placeDescriptions = new ArrayList<>();
                        int listOrganizationSize = dis.readInt();
                        for (int i = 0; i < listOrganizationSize; i++) {
                            Organization.PlaceDescription placeDescription = new Organization.PlaceDescription();
                         placeDescriptions.add(placeDescription);

                        }
                        OrganizationSection organizationSection = new OrganizationSection(new Organization(null, placeDescriptions));
                        resumeIn.addSection(type,organizationSection );
                        break;
                    case EDUCATION:
                        break;
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Map.Entry entry : resumeIn.getContacts().entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        for (Map.Entry entry : resumeIn.getSections().entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().toString());
        }

    }
}
