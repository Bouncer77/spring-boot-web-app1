package com.bouncer77.springbootapp1;

import com.bouncer77.springbootapp1.dao.PersonDao;
import com.bouncer77.springbootapp1.dao.RoleDao;
import com.bouncer77.springbootapp1.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SpringBootApp1Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApp1Application.class, args);
	}

	@Autowired
	PersonDao personDao;

	@Autowired
	RoleDao roleDao;

	@Override
	public void run(String... args) {

		// Person
		Person anna = new Person("annaEmail@google.com", "123456");
		Person dima = new Person("dimaEmail@google.com", "654321");
		List<Person> people = Arrays.asList(anna, dima);
		personDao.saveAll(people);
		System.out.println("<<<<<<<<<" + personDao.findAll());
	}

}
