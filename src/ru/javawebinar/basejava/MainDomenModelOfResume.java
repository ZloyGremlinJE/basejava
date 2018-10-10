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

        List<String>  qualifications = new ArrayList<>();
        qualifications.add("Квалификация1");
        qualifications.add("Квалификация2");

        List<WorkStudyPlace> worksPlaces = new ArrayList<>();
        worksPlaces.add(new WorkStudyPlace("Work1", "Date1", "Position1", "AboutPosition1"));
        worksPlaces.add(new WorkStudyPlace("Work2", "Date2", "Position2", "AboutPosition2"));

        List<WorkStudyPlace> studyPlaces = new ArrayList<>();
        studyPlaces.add(new WorkStudyPlace("Study1", "Date1", "Position1", "AboutPosition1"));
        studyPlaces.add(new WorkStudyPlace("Study2", "Date2", "Position2", "AboutPosition2"));

        Section section = new ListSection(achievements);
        resume.section.put(SectionType.ACHIEVEMENT, section);

        section = new ListSection(qualifications);
        resume.section.put(SectionType.QUALIFICATIONS, section);

        section = new WorkStudySection(studyPlaces);
        resume.section.put(SectionType.EDUCATION, section);

        section = new WorkStudySection(worksPlaces);
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
