package com.bouncer77.springbootapp1;

import com.bouncer77.springbootapp1.entity.*;
import com.bouncer77.springbootapp1.repository.AuthorRepository;
import com.bouncer77.springbootapp1.repository.BookRepository;
import com.bouncer77.springbootapp1.repository.PersonRepository;
import com.bouncer77.springbootapp1.repository.TagRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

@SpringBootApplication
public class SpringBootApp1Application {

    private static final Logger log = LoggerFactory.getLogger(SpringBootApp1Application.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp1Application.class, args);
    }

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Метод возвращает CommandLineRunnerкомпонент , который автоматически запускает код при запуске приложения.
     */
    @Bean
    public CommandLineRunner demo(PersonRepository personRepository,
                                  AuthorRepository authorRepository,
                                  BookRepository bookRepository,
                                  TagRepository tagRepository) {
        return (args) -> {
            // save a few persons
            // anna = person 1

            String annaPassword = "1";
            // Шифрование
            annaPassword = bCryptPasswordEncoder.encode(annaPassword);

            Person anna = new Person("a", "admin@google.com", annaPassword,
                    "Админ", "Админов");
            anna.setActive(true);
            Set<Role> annaRoleSet = new HashSet<>(Arrays.asList(Role.STUDENT, Role.TEACHER, Role.ADMIN));
            anna.setRoles(annaRoleSet);
            Passport passportAnna = new Passport("111", "456789");
            anna.setPassport(passportAnna);
            Phone phoneAnna1 = new Phone("89681110101");
            Phone phoneAnna2 = new Phone("89681110102");
            Set<Phone> phoneSetAnna = new HashSet<>(Arrays.asList(phoneAnna1, phoneAnna2));
            anna.setPhones(phoneSetAnna);
            // dima = person 2

            String dimaPassword = "654321";
            // Шифрование
            dimaPassword = bCryptPasswordEncoder.encode(dimaPassword);

            Person dima = new Person("DimaLogin", "dimaEmail@google.com", dimaPassword,
                    "Dima", "Ivanov");
            dima.setActive(true);
            Set<Role> dimaRoleSet = new HashSet<>(Arrays.asList(Role.STUDENT, Role.TEACHER));
            dima.setRoles(dimaRoleSet);
            Passport passportDima = new Passport("222", "456555");
            dima.setPassport(passportDima);
            Phone phoneDima1 = new Phone("89681110201");
            Set<Phone> phoneSetDima= new HashSet<>(Collections.singletonList(phoneDima1));
            dima.setPhones(phoneSetDima);

            // alex = person 3

            String alexPassword = "321";
            // Шифрование
            alexPassword = bCryptPasswordEncoder.encode(alexPassword);

            Person alex = new Person("AlexLogin", "alexEmail@gmail.com", alexPassword, "Alex", "Andreev");
            alex.setActive(true);
            alex.setRoles(Collections.singleton(Role.STUDENT));
            Passport passportAlex = new Passport("111", "456000");
            alex.setPassport(passportAlex);
            Phone phoneAlex1 = new Phone("89681110301");
            Set<Phone> phoneSetAlex = new HashSet<>(Collections.singletonList(phoneAlex1));
            alex.setPhones(phoneSetAlex);

            // marina = person 4

            String marinaPassword = "4455";
            // Шифрование
            marinaPassword = bCryptPasswordEncoder.encode(marinaPassword);

            Person marina = new Person("MarinaLogin", "marinaEmail@gmail.com", marinaPassword, "Marina", "Brejneva");
            marina.setActive(true);
            Set<Role> marinaRoleSet = new HashSet<>(Arrays.asList(Role.STUDENT, Role.MODERATOR));
            marina.setRoles(marinaRoleSet);
            Passport passportMarina = new Passport("123", "456004");
            marina.setPassport(passportMarina);
            Phone phoneMarina1 = new Phone("89681110402");
            Set<Phone> phoneSetMarina = new HashSet<>(Collections.singletonList(phoneMarina1));
            marina.setPhones(phoneSetMarina);

            List<Person> people = Arrays.asList(anna, dima, alex, marina);
            personRepository.saveAll(people);

            // fetch all persons
            log.info("Persons found with findAll():");
            log.info("-------------------------------");
            for (Person person : personRepository.findAll()) {
                log.info(person.toString());
            }
            log.info("");

            Author dostoevsky = new Author("Федор",  "Достоевский");
            Author bulgakov = new Author("Михаил",  "Булгаков");
            Author tolstoy = new Author("Лев",  "Толстой");
            Author pushkin = new Author("Александр",  "Пушкин");
            Author gogol = new Author("Николай",  "Гоголь");
            Author schildt = new Author("Герберт",  "Шилдт");

            // Для тестирования на удаление
            Author deleteTestAuthor = new Author("Иван",  "Удалятович");

            Author bates = new Author("Берт",  "Бэйтс");
            Author sierra = new Author("Кэти",  "Сьерра");
            List<Author> authorList = Arrays.asList(dostoevsky, bulgakov, tolstoy, pushkin, gogol, schildt, bates, sierra, deleteTestAuthor);
            authorRepository.saveAll(authorList);

            Tag classicLiteratureTag = new Tag("Классическая литература");
            Tag javaTag = new Tag("Java");
            Tag progTag = new Tag("Программирование");
            List<Tag> tagList = Arrays.asList(classicLiteratureTag, javaTag, progTag);
            tagRepository.saveAll(tagList);

            Book completeGuideJava = new Book("Java 8. Полное руководство.", 1377, schildt, javaTag);
            Book warAndPeace = new Book("Война и мир", 1300, dostoevsky, classicLiteratureTag);
            Book turbineDays = new Book("Дни Трубиных", 224, bulgakov, classicLiteratureTag);

            Set<Author> authorSet = new HashSet<>(Arrays.asList(bates, sierra));
            Set<Tag> tagSet = new HashSet<>(Arrays.asList(progTag, javaTag));
            Book headFirstJava = new Book("Изучаем Java", 690, authorSet, tagSet);

            List<Book> bookList = Arrays.asList(completeGuideJava, warAndPeace, turbineDays, headFirstJava);
            bookRepository.saveAll(bookList);
        };
    }
}
