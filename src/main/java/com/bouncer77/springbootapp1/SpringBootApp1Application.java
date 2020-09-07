package com.bouncer77.springbootapp1;

import com.bouncer77.springbootapp1.entity.Passport;
import com.bouncer77.springbootapp1.entity.Phone;
import com.bouncer77.springbootapp1.repository.PersonRepository;
import com.bouncer77.springbootapp1.entity.Person;
import com.bouncer77.springbootapp1.entity.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class SpringBootApp1Application implements CommandLineRunner {

    //private static final Logger log = LoggerFactory.getLogger(SpringBootApp1Application.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp1Application.class, args);
    }

    @Autowired
    PersonRepository personRepository;

    @Override
    public void run(String... args) {

        // save a few persons
            // anna = person 1
        Person anna = new Person("AnnaLogin", "annaEmail@google.com", "123",
                "Anna", "Goncharova" );
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
        System.out.println("<<<<<<<<<" + personRepository.findAll());

        System.out.println("\n\n");
        Passport passport = anna.getPassport();
        System.out.println(passport);
    }

}
