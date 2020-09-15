package com.bouncer77.springbootapp1.controller;

import com.bouncer77.springbootapp1.entity.Person;
import com.bouncer77.springbootapp1.entity.Role;
import com.bouncer77.springbootapp1.form.PersonForm;
import com.bouncer77.springbootapp1.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 03.09.2020
 */

@Controller
// @RequestMapping("/person")
public class WebPersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    // Вводится (inject) из application.properties.


    @Value("${error.message}")
    private String errorMessage;

    @Value("${error.message.person.exists}")
    private String personExists;

    @GetMapping("/deletePerson")
    public String deletePerson(@RequestParam(name = "id") long id,
                               Model model) {

        Person person = personService.read(id);
        if (Objects.nonNull(person)) {
            personService.delete(id);
            model.addAttribute("opDelete", true);
            model.addAttribute("deletedPerson", person);
        } else {
            model.addAttribute("opDelete", false);
        }

        return showAllPersons("", model);
    }

    // /editPerson?id={}
    @GetMapping("/editPerson")
    public String editPersonGet(@RequestParam(name = "id") long id,
                                Model model) {

        Person person = personService.read(id);

        if (Objects.isNull(person)) {
            model.addAttribute("isError", true);
            model.addAttribute("errorMsg", "Пользователь с указанным id не найден!");
            return showAllPersons("", model);
        }

        PersonForm personForm = new PersonForm(person.getLogin(), person.getEmail(), person.getName(), person.getSurname());
        model.addAttribute("personForm", personForm);
        model.addAttribute("id", id);
        model.addAttribute("roles", Role.values());

        return "/person/editPerson";
    }

    @PostMapping("/editPerson")
    public String editPersonPut(@RequestParam(name = "id") long id, Model model,
                                @ModelAttribute("personForm") PersonForm personForm) {

        if (personService.update(id, personForm)) {
            return showAllPersons("", model);
        } else {
            model.addAttribute("errorMessage", errorMessage);
            return "/person/editPerson";
        }
    }

    @GetMapping("/personList")
    public String showAllPersons(@RequestParam(required = false) String filter,
                                 Model model) {

        List<Person> persons;
        if (filter != null && !filter.isEmpty()) {
            persons = personService.readsByName(filter);
        } else {
            persons = personService.readAll();
        }

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

        Person personDb = personService.read(personForm.getLogin());
        if (Objects.nonNull(personDb)) {
            model.addAttribute("errorMessage", personExists);
            return "/person/addPerson";
        } else {
            personService.create(personForm);
            return "redirect:/personList";
        }
    }
}
