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
        Resume resume = new Resume("UUID1", "Григорий Кислин");

        Map<ContactsType, String> contacts = resume.getContacts();
        Map<SectionsType, Section> sections = resume.getSections();

        contacts.put(ContactsType.PHONE,"+7(921) 855-0482");
        contacts.put(ContactsType.SKYPE, "grigory.kislin");
        contacts.put(ContactsType.EMAIL, "gkislin@yandex.ru");
        contacts.put(ContactsType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        contacts.put(ContactsType.GITHUB, "https://github.com/gkislin");
        contacts.put(ContactsType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/gkislin");
        contacts.put(ContactsType.HOMEPAGE,"http://gkislin.ru");

        sections.put(SectionsType.OBJECTIVE, new SimpleTextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        sections.put(SectionsType.PERSONAL,  new SimpleTextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры. "));

        List<String> achievements = new ArrayList<>();
        achievements.add("\n * С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven." +
                " \n Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". " +
                "\n Организация онлайн стажировок и ведение проектов. Более 1000 выпускников. ");

        achievements.add("\n * Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio," +
                " \n DuoSecurity, Google Authenticator, Jira, Zendesk. ");

        achievements.add("\n * Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM," +
                " CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO" +
                " \n аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");

        achievements.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT," +
                " \n ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга. ");

        achievements.add("\n * Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, " +
                " JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. " +
                "\n Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");

        achievements.add("\n * Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay," +
                "\n Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");

        Section section = new ListSection(achievements);
        sections.put(SectionsType.ACHIEVEMENT, section);

        List<String>  qualifications = new ArrayList<>();
        qualifications.add("\n * JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2 ");
        qualifications.add("\n * Version control: Subversion, Git, Mercury, ClearCase, Perforce ");
        qualifications.add("\n * DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle");
        qualifications.add("\n * MySQL, SQLite, MS SQL, HSQLDB");
        qualifications.add("\n * Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy");
        qualifications.add("\n * XML/XSD/XSLT, SQL, C/C++, Unix shell scripts");
        qualifications.add("\n * Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA " +
                "\n(Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit," +
                "\nSelenium(htmlelements).");
        qualifications.add("\n * Python: Django.");
        qualifications.add("\n * JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js ");
        qualifications.add("\n * Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        qualifications.add("\n * Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX," +
                " \n JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT. ");
        qualifications.add("\n * PИнструменты: Maven + plugin development, Gradle, настройка Ngnix, ");
        qualifications.add("\n * администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita," +
                "\n pgBouncer. ");
        qualifications.add("\n * Отличное знание и опыт применения концепций ООП, SOA, шаблонов");
        qualifications.add("\n * проектрирования, архитектурных шаблонов, UML, функционального ");
        qualifications.add("\n * программирования ");
        qualifications.add("\n * Родной русский, английский \"upper intermediate\" ");

        section = new ListSection(qualifications);
        sections.put(SectionsType.QUALIFICATIONS, section);

        List<Company> worksPlaces = new ArrayList<>();
        worksPlaces.add(new Company("Java Online Projects", "http://javaops.ru", LocalDate.of(2013, Month.OCTOBER, 01), LocalDate.now(),
                "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок."));
        worksPlaces.add(new Company("Wrike", "https://www.wrike.com/",LocalDate.of(2012, Month.OCTOBER, 01), LocalDate.of(2006, Month.JANUARY,01),
                "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring," +
                "\n MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."));
        section = new CompanySection(worksPlaces);
        sections.put(SectionsType.EXPERIENCE, section);

        List<Company> studyPlaces = new ArrayList<>();
        studyPlaces.add(new Company("Coursera", "https://www.coursera.org/course/progfun",LocalDate.of(2013, Month.MARCH, 01), LocalDate.of(2013, Month.MAY, 01),
                "Functional Programming Principles in Scala\" by Martin Odersky", ""));
        studyPlaces.add(new Company("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366", LocalDate.of(2011, Month.MARCH, 01), LocalDate.of(2011, Month.APRIL, 01),
                "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"", ""));
        section = new CompanySection(studyPlaces);
        sections.put(SectionsType.EDUCATION, section);


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
