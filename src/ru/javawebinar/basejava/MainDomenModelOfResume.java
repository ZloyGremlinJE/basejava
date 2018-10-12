package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainDomenModelOfResume {


    public static void main(String[] args) {
        //TODO add real data from resume of author
        //in
        Resume resume = new Resume("UUID1", "Jon Dou");

        Map<ContactsType, String> contacts = resume.getContacts();
        Map<SectionsType, Section> sections = resume.getSections();

        contacts.put(ContactsType.PHONE,"2-12-46");
        contacts.put(ContactsType.EMAIL, "jon@gmail.com");

        sections.put(SectionsType.OBJECTIVE, new SimpleTextSection("CEO"));
        sections.put(SectionsType.PERSONAL,  new SimpleTextSection("best of the best"));

        List<String> achievements = new ArrayList<>();
        achievements.add("Достижение1");
        achievements.add("Достижение2");
        Section section = new ListSection(achievements);
        sections.put(SectionsType.ACHIEVEMENT, section);

        List<String>  qualifications = new ArrayList<>();
        qualifications.add("Квалификация1");
        qualifications.add("Квалификация2");
        section = new ListSection(qualifications);
        sections.put(SectionsType.QUALIFICATIONS, section);

        List<Company> worksPlaces = new ArrayList<>();
        worksPlaces.add(new Company("Work1", "url1", LocalDate.of(1979, Month.APRIL, 21), LocalDate.of(1979, Month.APRIL, 21),"Position1", "AboutPosition1"));
        worksPlaces.add(new Company("Work2", "url2",LocalDate.of(1979, Month.APRIL, 21), LocalDate.of(1979, Month.APRIL,21), "Position2", "AboutPosition2"));
        section = new CompanySection(worksPlaces);
        sections.put(SectionsType.EDUCATION, section);

        List<Company> studyPlaces = new ArrayList<>();
        studyPlaces.add(new Company("Study1", "url3",LocalDate.of(1979, Month.APRIL, 21), LocalDate.of(1979, Month.APRIL, 21), "Position1", "AboutPosition1"));
        studyPlaces.add(new Company("Study2", "url4", LocalDate.of(1979, Month.APRIL, 21), LocalDate.of(1979, Month.APRIL, 21), "Position2", "AboutPosition2"));
        section = new CompanySection(studyPlaces);
        sections.put(SectionsType.EXPERIENCE, section);


       //out
        System.out.println(resume.getFullName());

         for (Map.Entry entry : contacts.entrySet()){
             System.out.println(entry.getKey() + " " + entry.getValue());
         }

         for (Map.Entry entry : sections.entrySet()){
             System.out.println(entry.getKey() + " " + entry.getValue().toString());
         }



    }
}
