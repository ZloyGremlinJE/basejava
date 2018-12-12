package ru.javawebinar.basejava;

public class MainString {


    public static void main(String[] args)  {

    }




}


//    protected static final File STORAGE_DIR = new File("D:\\basejava\\storage");


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

//        //input
//        Resume resumeOut = ResumeTestData.getResumeTestData();
//        Map<ContactType, String> contacts = resumeOut.getContacts();
//        Map<SectionType, Section> sections = resumeOut.getSections();
//
//        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("D:\\basejava\\storage\\resumeTest.bin"))) {
//
//            dos.writeUTF(resumeOut.getUuid());
//            dos.writeUTF(resumeOut.getFullName());
//
//            dos.writeInt(contacts.size());
//            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
//                dos.writeUTF(entry.getKey().name());
//                dos.writeUTF(entry.getValue());
//            }
//
//            dos.writeInt(sections.size());
//            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
//                SectionType sectionType = entry.getKey();
//                dos.writeUTF(entry.getKey().name());
//                Section section = entry.getValue();
//                switch (sectionType) {
//                    case PERSONAL:
//                    case OBJECTIVE:
//                        String content = ((TextSection) section).getContent();
//                        dos.writeUTF(content);
//                        break;
//                    case ACHIEVEMENT:
//                    case QUALIFICATIONS:
//                        List list = ((ListSection) section).getItems();
//                        int size = list.size();
//                        dos.writeInt(size);
//                        for (int i = 0; i < size; i++) {
//                            dos.writeUTF((String) list.get(i));
//                        }
//                        break;
//
//                    case EXPERIENCE:
//                    case EDUCATION:
//                        List listofOrganizations = ((OrganizationSection) section).getOrganizations();
//                        int sizeListOrganization = listofOrganizations.size();
//                        dos.writeInt(sizeListOrganization);
//                        for (int i = 0; i < sizeListOrganization; i++) {
//                            Organization organization = (Organization) listofOrganizations.get(i);
//                            Link link = organization.getHomepage();
//                            dos.writeUTF(link.getName());
//                            dos.writeUTF(link.getUrl());
//                            List<Organization.PlaceDescription> placeDescriptions = organization.getPlaceDescriptions();
//                            int sizeListDescriptions = placeDescriptions.size();
//                            dos.writeInt(sizeListDescriptions);
//                            for (int j = 0; j < sizeListDescriptions; j++) {
//                                Organization.PlaceDescription placeDescription = placeDescriptions.get(j);
//                                dos.writeUTF(placeDescription.getStartDate().toString());
//                                dos.writeUTF(placeDescription.getEndDate().toString());
//                                dos.writeUTF(placeDescription.getTitle());
//                                if (placeDescription.getDescription() != null) {
//                                    dos.writeUTF(placeDescription.getDescription());
//                                } else {
//                                    dos.writeUTF("");
//                                }
//                            }
//                        }
//
//                        break;
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        //output
//        Resume resumeIn = null;
//        try (DataInputStream dis = new DataInputStream(new FileInputStream("D:\\basejava\\storage\\resumeTest.bin"))) {
//            String uuid = dis.readUTF();
//            String fullName = dis.readUTF();
//            resumeIn = new Resume(uuid, fullName);
//            int sizeOutputContacts = dis.readInt();
//            for (int i = 0; i < sizeOutputContacts; i++) {
//                resumeIn.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
//            }
//
//            int sizeOutputSection = dis.readInt();
//            for (int k = 0; k < sizeOutputSection; k++) {
//                String typeOf = dis.readUTF();
//                SectionType type = SectionType.valueOf(typeOf);
//                switch (type) {
//                    case PERSONAL:
//                    case OBJECTIVE:
//                        resumeIn.addSection(type, new TextSection(dis.readUTF()));
//                        break;
//
//                    case ACHIEVEMENT:
//                    case QUALIFICATIONS:
//                        List<String> list = new ArrayList<>();
//                        int listsize = dis.readInt();
//                        for (int i = 0; i < listsize; i++) {
//                            String item = dis.readUTF();
//                            list.add(item);
//                        }
//                        resumeIn.addSection(type, new ListSection(list));
//                        break;
//
//                    case EXPERIENCE:
//                    case EDUCATION:
//                        List<Organization> organizations = new ArrayList<>();
//                        List<Organization.PlaceDescription> placeDescriptions = new ArrayList<>();
//                        int listOrganizationSize = dis.readInt();
//                        for (int i = 0; i < listOrganizationSize; i++) {
//                          Link link = new Link(dis.readUTF(), dis.readUTF());
//                          int sizelistDescriptions = dis.readInt();
//                            for (int j = 0; j < sizelistDescriptions; j++) {
//                                LocalDate startDay = LocalDate.parse(dis.readUTF());
//                                LocalDate endDate = LocalDate.parse(dis.readUTF());
//                                String title = dis.readUTF();
//                                String description = dis.readUTF();
//                                placeDescriptions.add(new Organization.PlaceDescription(startDay, endDate, title, description));
//                            }
//                            organizations.add(new Organization(link, placeDescriptions));
//                        }
//
//                        OrganizationSection organizationSection = new OrganizationSection(organizations);
//                        resumeIn.addSection(type, organizationSection);
//                        break;
//                }
//
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        for (Map.Entry entry : resumeIn.getContacts().entrySet()) {
//            System.out.println(entry.getKey() + " " + entry.getValue());
//        }
//
//        for (Map.Entry entry : resumeIn.getSections().entrySet()) {
//            System.out.println(entry.getKey() + " " + entry.getValue().toString());
//        }
//
//    }

