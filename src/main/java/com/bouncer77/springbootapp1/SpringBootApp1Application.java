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
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableAspectJAutoProxy
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
            // ivan = person 1
            Person ivan = Person.builder()
                    .active(true)
                    .name("Ivan")
                    .surname("Kosenkov")
                    .roles(Arrays.asList(Role.STUDENT, Role.TEACHER, Role.ADMIN))
                    .email("ivan@gmail.com")
                    .login("i")
                    .password(bCryptPasswordEncoder.encode("123"))
                    .build();

            // anton = person 2
            Person anton = Person.builder()
                    .active(true)
                    .name("Anton")
                    .surname("Petrov")
                    .role(Role.STUDENT)
                    .email("anton@gmail.com")
                    .login("antonLogin")
                    .password(bCryptPasswordEncoder.encode("123"))
                    .build();

            // anna = person 3
            Person anna = Person.builder()
                    .active(true)
                    .name("Anna")
                    .surname("Petrova")
                    .roles(Arrays.asList(Role.STUDENT, Role.TEACHER, Role.ADMIN))
                    .email("anna@gmail.com")
                    .login("a")
                    .password(bCryptPasswordEncoder.encode("1"))
                    .phones(Arrays.asList(new Phone("89681110101"), new Phone("89681110102")))
                    .passport(new Passport("111", "456789"))
                    .build();

            // dima = person 4
            Person dima = Person.builder()
                    .active(true)
                    .name("Dima")
                    .surname("Ivanov")
                    .roles(Arrays.asList(Role.STUDENT, Role.TEACHER))
                    .email("dimaEmail@gmail.com")
                    .login("DimaLogin")
                    .password(bCryptPasswordEncoder.encode("654321"))
                    .phone(new Phone("89681110201"))
                    .passport(new Passport("222", "456555"))
                    .build();

            // alex = person 5
            Person alex = Person.builder()
                    .active(true)
                    .name("Alex")
                    .surname("Andreev")
                    .role(Role.STUDENT)
                    .email("alexEmail@gmail.com")
                    .login("AlexLogin")
                    .password(bCryptPasswordEncoder.encode("321"))
                    .phone(new Phone("89681110301"))
                    .passport(new Passport("111", "456000"))
                    .build();

            // marina = person 6
            Person marina = Person.builder()
                    .active(true)
                    .name("Marina")
                    .surname("Brejneva")
                    .roles(Arrays.asList(Role.STUDENT, Role.MODERATOR))
                    .email("marinaEmail@gmail.com")
                    .login("MarinaLogin")
                    .password(bCryptPasswordEncoder.encode("123"))
                    .phone(new Phone("89681110402"))
                    .passport(new Passport("123", "456004"))
                    .build();

            List<Person> people = Arrays.asList(ivan, anton, anna, dima, alex, marina);
            personRepository.saveAll(people);

            // fetch all persons
            /*log.info("Persons found with findAll():");
            log.info("-------------------------------");
            for (Person person : personRepository.findAll()) {
                log.info(person.toString());
            }
            log.info("");*/

            Author dostoevsky = new Author("Федор", "Достоевский");
            Author bulgakov = new Author("Михаил", "Булгаков");
            Author tolstoy = new Author("Лев", "Толстой");
            Author pushkin = new Author("Александр", "Пушкин");
            Author gogol = new Author("Николай", "Гоголь");
            Author schildt = new Author("Герберт", "Шилдт");

            // Для тестирования на удаление
            Author deleteTestAuthor = new Author("Иван", "Удалятович");

            Author bates = new Author("Берт", "Бэйтс");
            Author sierra = new Author("Кэти", "Сьерра");
            List<Author> authorList = Arrays.asList(dostoevsky, bulgakov, tolstoy, pushkin, gogol, schildt, bates, sierra, deleteTestAuthor);
            authorRepository.saveAll(authorList);

            Tag classicLiteratureTag = new Tag("Классическая литература");
            Tag javaTag = new Tag("Java");
            Tag progTag = new Tag("Программирование");
            List<Tag> tagList = Arrays.asList(classicLiteratureTag, javaTag, progTag);
            tagRepository.saveAll(tagList);

            //Book completeGuideJava = new Book("Java 8. Полное руководство.", 1377, schildt, javaTag);
            Book completeGuideJava = Book.builder()
                    .name("Java 8. Полное руководство")
                    .lastPage(1377)
                    .author(schildt)
                    .tag(javaTag)
                    .build();

            // Book warAndPeace = new Book("Война и мир", 1300, dostoevsky, classicLiteratureTag);
            Book warAndPeace = Book.builder()
                    .name("Война и мир")
                    .lastPage(1300)
                    .author(dostoevsky)
                    .tag(classicLiteratureTag)
                    .build();

            //Book turbineDays = new Book("Дни Трубиных", 224, bulgakov, classicLiteratureTag);
            Book turbineDays = Book.builder()
                    .name("Дни Трубиных")
                    .lastPage(244)
                    .author(bulgakov)
                    .tag(classicLiteratureTag)
                    .build();


            /*Set<Author> authorSet = new HashSet<>(Arrays.asList(bates, sierra));
            Set<Tag> tagSet = new HashSet<>(Arrays.asList(progTag, javaTag));
            Book headFirstJava = new Book("Изучаем Java", 690, authorSet, tagSet);*/
            Book headFirstJava = Book.builder()
                    .name("Изучаем Java")
                    .lastPage(690)
                    .authors(Arrays.asList(bates, sierra))
                    .tags(Arrays.asList(progTag, javaTag))
                    .build();

            List<Book> bookList = Arrays.asList(completeGuideJava, warAndPeace, turbineDays, headFirstJava);
            bookRepository.saveAll(bookList);
        };
    }
}
