package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.DateUtil;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResumeTestData {
    public static Resume getResumeTestData(){
        Resume resume = new Resume("uuid1", "Григорий Кислин");

        Map<ContactType, String> contacts = resume.getContacts();
        Map<SectionType, Section> sections = resume.getSections();

        contacts.put(ContactType.PHONE, "+7(921) 855-0482");
        contacts.put(ContactType.SKYPE, "grigory.kislin");
        contacts.put(ContactType.EMAIL, "gkislin@yandex.ru");
        contacts.put(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        contacts.put(ContactType.GITHUB, "https://github.com/gkislin");
        contacts.put(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/gkislin");
        contacts.put(ContactType.HOMEPAGE, "http://gkislin.ru");

        sections.put(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java" +
                " Web и Enterprise технологиям"));

        sections.put(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, " +
                "инициативность. Пурист кода и архитектуры. "));

        List<String> achievements = new ArrayList<>();
        achievements.add("\n * С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"" +
                "Многомодульный maven. \n Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное" +
                " взаимодействие (JMS/AKKA)\". \n Организация онлайн стажировок и ведение проектов. Более 1000 выпускников. ");
        achievements.add("\n * Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike." +
                " Интеграция с Twilio," + " \n DuoSecurity, Google Authenticator, Jira, Zendesk. ");
        achievements.add("\n * Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. " +
                "Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке:" +
                " Scala/Play/Anorm/JQuery. Разработка SSO \n аутентификации и авторизации различных ERP модулей," +
                " интеграция CIFS/SMB java сервера.");
        achievements.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring," +
                " Spring-MVC, GWT,\n ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга. ");
        achievements.add("\n * Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, " +
                " JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. " +
                "\n Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        achievements.add("\n * Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat," +
                " Eport, Chronopay,\n Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        Section section = new ListSection(achievements);
        sections.put(SectionType.ACHIEVEMENT, section);


        List<String> qualifications = new ArrayList<>();
        qualifications.add("\n * JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2 ");
        qualifications.add("\n * Version control: Subversion, Git, Mercury, ClearCase, Perforce ");
        qualifications.add("\n * DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle");
        qualifications.add("\n * MySQL, SQLite, MS SQL, HSQLDB");
        qualifications.add("\n * Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy");
        qualifications.add("\n * XML/XSD/XSLT, SQL, C/C++, Unix shell scripts");
        qualifications.add("\n * Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring " +
                "(MVC, Security, Data, Clouds, Boot), JPA \n(Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT)," +
                " Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit,\nSelenium(htmlelements).");
        qualifications.add("\n * Python: Django.");
        qualifications.add("\n * JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js ");
        qualifications.add("\n * Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        qualifications.add("\n * Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, " +
                "DOM, XSLT, MDB, JMX,\n JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, " +
                "OAuth2, JWT. ");
        qualifications.add("\n * PИнструменты: Maven + plugin development, Gradle, настройка Ngnix, ");
        qualifications.add("\n * администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, " +
                "iReport, OpenCmis, Bonita,\n pgBouncer. ");
        qualifications.add("\n * Отличное знание и опыт применения концепций ООП, SOA, шаблонов");
        qualifications.add("\n * проектрирования, архитектурных шаблонов, UML, функционального ");
        qualifications.add("\n * программирования ");
        qualifications.add("\n * Родной русский, английский \"upper intermediate\" ");
        section = new ListSection(qualifications);
        sections.put(SectionType.QUALIFICATIONS, section);


        List<Organization> worksPlaces = new ArrayList<>();
        Organization organization = new Organization("Java Online Projects", "http://javaops.ru");
        organization.addDescriptPlace(
                new DescriptPlace(
                        DateUtil.of(2013, Month.OCTOBER), LocalDate.now(),
                        "Автор проекта.",
                        "Создание, организация и проведение Java онлайн проектов и стажировок."));
        worksPlaces.add(organization);
        organization = new Organization("Wrike", "https://www.wrike.com/");
        organization.addDescriptPlace(new DescriptPlace(
                DateUtil.of(2006, Month.JANUARY), DateUtil.of(2006, Month.JANUARY),
                "Старший разработчик (backend)",
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring," +
                        "\n MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, " +
                        "авторизация по OAuth1, OAuth2, JWT SSO."));
        worksPlaces.add(organization);
        section = new OrganizationSection(worksPlaces);
        sections.put(SectionType.EXPERIENCE, section);


        List<Organization> studyPlaces = new ArrayList<>();
        organization = new Organization("Санкт-Петербургский национальный исследовательский университет" +
                " информационных технологий, механики и оптики",
                "http://www.ifmo.ru/ru/");
        organization.addDescriptPlace(new DescriptPlace(DateUtil.of(1993, Month.SEPTEMBER),DateUtil.of(1996, Month.JULY),
                "Аспирантура (программист С, С++)",""));
        organization.addDescriptPlace(new DescriptPlace(DateUtil.of(1987, Month.SEPTEMBER),DateUtil.of(1993, Month.JULY),
                "Инженер (программист Fortran, C)",""));
        studyPlaces.add(organization);
        section = new OrganizationSection(studyPlaces);
        sections.put(SectionType.EDUCATION, section);

        return resume;
    }
}
