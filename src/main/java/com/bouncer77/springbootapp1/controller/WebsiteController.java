package com.bouncer77.springbootapp1.controller;

import com.bouncer77.springbootapp1.repository.PersonRepository;
import com.bouncer77.springbootapp1.form.PersonForm;
import com.bouncer77.springbootapp1.entity.Person;
import com.bouncer77.springbootapp1.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Objects;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 03.09.2020
 */

@Controller
// @RequestMapping("/person")
public class WebsiteController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    // Вводится (inject) из application.properties.
    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @Value("${error.message.person.exists}")
    private String personExists;

    @GetMapping(value = { "/", "/index" })
    public String index(Model model) {

        model.addAttribute("message", message);

        //return "index";
        return "main";
    }

    @GetMapping("/personList")
    public String showAllPersons(@RequestParam(required = false) String filter,
            Model model) {
        // List<Person> persons = personDao.findAll();


        Iterable<Person> persons;
        if (filter != null && !filter.isEmpty()) {
            persons = personRepository.findByName(filter);
        } else {
            persons = personRepository.findAll();
        }


        // persons.forEach(System.out::println);
        model.addAttribute("persons", persons);
        return "/person/personList";
    }

    @GetMapping("/addPerson")
    public String showAddPersonPage(Model model) {

        PersonForm personForm = new PersonForm();
        model.addAttribute("personForm", personForm);

        return "/person/addPerson";
    }

    @PostMapping("/addPerson")
    public String addPerson(Model model,
                            @ModelAttribute("personForm") PersonForm personForm) {

        String login = personForm.getLogin();
        String email = personForm.getEmail();
        String password = personForm.getPassword();
        String name = personForm.getName();
        String surname = personForm.getSurname();

        Person personDb = personRepository.findByLogin(login);
        if (Objects.nonNull(personDb)) {
            model.addAttribute("errorMessage", personExists);
            return "/person/addPerson";
        }

        if (email != null && email.length() > 0 && email.matches("\\w+@\\w+\\.\\w+")//
                && password != null && password.length() > 0) {

            Person person = new Person(login, email, password, name, surname);
            person.setActive(true);
            person.setRoles(Collections.singleton(Role.STUDENT));

            // Шифрование пароля
            person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));

            personRepository.save(person);

            return "redirect:/personList";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "/person/addPerson";
    }

    @GetMapping("/about_cpm")
    public String showAboutCPM() {
        return "/about_cpm";
    }

    @GetMapping("/contact")
    public String showContact() {
        return "/contact";
    }

    @GetMapping("/applications")
    public String showApplications() {
        return "/applications";
    }
}
