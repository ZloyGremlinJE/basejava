package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainDomenModelOfResume {


    public static void main(String[] args) {
        //in
        Resume resume = new Resume("UUID1", "Jon Dou");
        resume.contacts.put(ContactsType.PHONE,"2-12-46");
        resume.contacts.put(ContactsType.EMAIL, "jon@gmail.com");
        resume.section.put(SectionType.OBJECTIVE, new SimpleTextSection("CEO"));
        resume.section.put(SectionType.PERSONAL,  new SimpleTextSection("best of the best"));

        List<String> achievements = new ArrayList<>();
        achievements.add("Достижение1");
        achievements.add("Достижение2");
        Section section = new ListSection(achievements);
        resume.section.put(SectionType.ACHIEVEMENT, section);

        List<String>  qualifications = new ArrayList<>();
        qualifications.add("Квалификация1");
        qualifications.add("Квалификация2");
        section = new ListSection(qualifications);
        resume.section.put(SectionType.QUALIFICATIONS, section);

        List<Company> worksPlaces = new ArrayList<>();
        worksPlaces.add(new Company("Work1", "url1", "Date1", "Position1", "AboutPosition1"));
        worksPlaces.add(new Company("Work2", "url2","Date2", "Position2", "AboutPosition2"));
        section = new CompanySection(worksPlaces);
        resume.section.put(SectionType.EDUCATION, section);

        List<Company> studyPlaces = new ArrayList<>();
        studyPlaces.add(new Company("Study1", "url3","Date1", "Position1", "AboutPosition1"));
        studyPlaces.add(new Company("Study2", "url4", "Date2", "Position2", "AboutPosition2"));
        section = new CompanySection(studyPlaces);
        resume.section.put(SectionType.EXPERIENCE, section);


       //out
        System.out.println(resume.getFullName());

         for (Map.Entry entry : resume.contacts.entrySet()){
             System.out.println(entry.getKey() + " " + entry.getValue());
         }

         for (Map.Entry entry : resume.section.entrySet()){
             System.out.println(entry.getKey() + " " + entry.getValue().toString());
         }



    }
}
