package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.time.Month;

public class ResumeTestData {
    public static Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);

        resume.addContact(ContactType.PHONE, "+7(921) 855-0482");
        resume.addContact(ContactType.SKYPE, "skype:username");
        resume.addContact(ContactType.MAIL, "test@example.com");
        resume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/username");
        resume.addContact(ContactType.GITHUB, "https://github.com/username");
        resume.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/username");
        resume.addContact(ContactType.HOME_PAGE, "https://www.mywebsite.com");

        resume.addSection(SectionType.PERSONAL, new TextSection("Активный и целеустремленный разработчик"));
        resume.addSection(SectionType.OBJECTIVE, new TextSection("Ведущий Java разработчик"));
        resume.addSection(SectionType.ACHIEVEMENTS, new ListSection(
                "Разработал систему автоматизации обработки данных",
                "Оптимизировал процесс разработки и сборки проекта"
        ));
        resume.addSection(SectionType.QUALIFICATIONS, new ListSection(
                "Java, Spring, Hibernate, SQL",
                "Разработка RESTful веб-сервисов"
        ));
        resume.addSection(SectionType.EXPERIENCE, new OrganizationSection(
                new Organization("Компания 1", "https://company1.com",
                        new Position(2019, Month.JANUARY, "Разработчик", "Разработка ПО")),
                new Organization("Компания 2", "https://company2.com",
                        new Position(2015, Month.MARCH, 2018, Month.DECEMBER, "Старший разработчик", "Разработка и поддержка систем"))
        ));

        resume.addSection(SectionType.EDUCATION, new OrganizationSection(
                new Organization("Университет 1", "https://university1.com",
                        new Position(LocalDate.of(2010, 9, 1), LocalDate.of(2014, 6, 1), "Студент", "Инженер программного обеспечения")))
        );


        return resume;
    }
}
