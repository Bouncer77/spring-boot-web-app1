package com.bouncer77.springbootapp1;

import com.bouncer77.springbootapp1.dao.PersonDao;
import com.bouncer77.springbootapp1.model.Person;
import com.bouncer77.springbootapp1.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class SpringBootApp1Application {//implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApp1Application.class, args);
	}

/*	@Autowired
	PersonDao personDao;

	@Override
	public void run(String... args) {

		// Person
		Person anna = new Person("AnnaLogin", "annaEmail@google.com", "123",
				"Анна", "Гончарова" );
		anna.setActive(true);
		anna.setRoles(Collections.singleton(Role.STUDENT));

		Person dima = new Person("DImaLogin", "dimaEmail@google.com", "654321",
				"Дима", "Иванов");
		dima.setActive(true);
		dima.setRoles(Collections.singleton(Role.STUDENT));

		List<Person> people = Arrays.asList(anna, dima);
		personDao.saveAll(people);
		System.out.println("<<<<<<<<<" + personDao.findAll());
	}*/

}
