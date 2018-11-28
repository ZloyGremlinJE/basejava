package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.model.Section;
import ru.javawebinar.basejava.model.SectionType;

import java.io.*;
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
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("D:\\basejava\\storage\\resumeTest.bin"))) {
            Resume resume = ResumeTestData.getResumeTestData();

            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());

            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            Map<SectionType, Section> sections = resume.getSections();
            dos.writeInt(contacts.size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                dos.writeUTF(entry.getKey().name());

            }



        } catch (IOException e) {
            e.printStackTrace();
        }


        //output
        try (DataInputStream dis = new DataInputStream(new FileInputStream("D:\\basejava\\storage\\resumeTest.bin"))) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
