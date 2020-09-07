package com.bouncer77.springbootapp1;

import com.bouncer77.springbootapp1.entity.*;
import com.bouncer77.springbootapp1.repository.AuthorRepository;
import com.bouncer77.springbootapp1.repository.BookRepository;
import com.bouncer77.springbootapp1.repository.PersonRepository;
import com.bouncer77.springbootapp1.repository.TagRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication
public class SpringBootApp1Application {

    private static final Logger log = LoggerFactory.getLogger(SpringBootApp1Application.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp1Application.class, args);
    }

    /**
     * Метод возвращает CommandLineRunnerкомпонент , который автоматически запускает код при запуске приложения.
     */
    @Bean
    public CommandLineRunner demo(PersonRepository personRepository,
                                  AuthorRepository authorRepository,
                                  BookRepository bookRepository,
                                  TagRepository tagRepository) {
        return (args) -> {
            /*// save a few persons
            // anna = person 1
            Person anna = new Person("AnnaLogin", "annaEmail@google.com", "123",
                    "Anna", "Goncharova");
            anna.setActive(true);
            anna.setRoles(Collections.singleton(Role.STUDENT));
            Passport passportAnna = new Passport("111", "456789");
            anna.setPassport(passportAnna);
            Phone phoneAnna1 = new Phone("89681110101");
            Phone phoneAnna2 = new Phone("89681110102");
            anna.setPhones(Arrays.asList(phoneAnna1, phoneAnna2));
            // dima = person 2
            Person dima = new Person("DimaLogin", "dimaEmail@google.com", "654321",
                    "Dima", "Ivanov");
            dima.setActive(true);
            dima.setRoles(Collections.singleton(Role.STUDENT));
            Passport passportDima = new Passport("222", "456555");
            dima.setPassport(passportDima);
            Phone phoneDima1 = new Phone("89681110201");
            dima.setPhones(Collections.singletonList(phoneDima1));

            // alex = person 3
            Person alex = new Person("AlexLogin", "alexEmail@gmail.com", "321", "Alex", "Andreev");
            alex.setActive(true);
            alex.setRoles(Collections.singleton(Role.STUDENT));
            Passport passportAlex = new Passport("111", "456000");
            alex.setPassport(passportAlex);
            Phone phoneAlex1 = new Phone("89681110301");
            alex.setPhones(Collections.singletonList(phoneAlex1));

            List<Person> people = Arrays.asList(anna, dima, alex);
            personRepository.saveAll(people);

            // fetch all persons
            log.info("Persons found with findAll():");
            log.info("-------------------------------");
            for (Person person : personRepository.findAll()) {
                log.info(person.toString());
            }
            log.info("");*/

            Author dostoevsky = new Author("Федор",  "Достоевский");
            Author bulgakov = new Author("Михаил",  "Булгаков");
            Author tolstoy = new Author("Лев",  "Толстой");
            Author pushkin = new Author("Александр",  "Пушкин");
            Author gogol = new Author("Николай",  "Гоголь");
            Author schildt = new Author("Герберт",  "Шилдт");
            List<Author> authorList = Arrays.asList(dostoevsky, bulgakov, tolstoy, pushkin, gogol, schildt);
            authorRepository.saveAll(authorList);

            Tag classicLiteratureTag = new Tag("Классическая литература");
            Tag javaTag = new Tag("Java");
            List<Tag> tagList = Arrays.asList(classicLiteratureTag, javaTag);
            tagRepository.saveAll(tagList);

            Book completeGuideJava = new Book("Java 8. Полное руководство.", 1377, schildt);
            completeGuideJava.getAuthors().add(schildt);
            //System.out.println(authorRepository.findBySurname("Шилдт").toString());
            //log.info(authorRepository.findBySurname("Шилдт").toString());

            //completeGuideJava.getAuthors().add(authorRepository.findBySurname("Шилдт"));

            Book warAndPeace = new Book("Война и мир", 1300, dostoevsky);
            Book turbineDays = new Book("Дни Трубиных", 224, bulgakov);
            List<Book> bookList = Arrays.asList(completeGuideJava, warAndPeace, turbineDays);
            bookRepository.saveAll(bookList);
        };
    }
}
