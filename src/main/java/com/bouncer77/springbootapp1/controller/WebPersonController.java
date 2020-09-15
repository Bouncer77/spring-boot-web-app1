package com.bouncer77.springbootapp1.controller;

import com.bouncer77.springbootapp1.entity.Person;
import com.bouncer77.springbootapp1.entity.Role;
import com.bouncer77.springbootapp1.form.PersonForm;
import com.bouncer77.springbootapp1.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 03.09.2020
 */

@Controller
@RequestMapping("/persons")
@RequiredArgsConstructor
public class WebPersonController {

    private final PersonService personService;

    @Value("${error.message}")
    String errorMessage;

    @Value("${error.message.person.exists}")
    String personExists;

    @GetMapping(value = "/{id}")
    public String showPerson(@PathVariable(name = "id") long id,
                                 Model model) {

        Person person = personService.read(id);
        if (Objects.nonNull(person)) {
            model.addAttribute("person", person);
        } else {
            model.addAttribute("errorMsg", "Person did not found");
        }
        return "/person/showPerson";
    }

    @GetMapping
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

    @GetMapping("/delete")
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

    @GetMapping("/edit")
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

    @PostMapping("/edit")
    public String editPersonPut(@RequestParam(name = "id") long id, Model model,
                                @ModelAttribute("personForm") PersonForm personForm) {

        if (personService.update(id, personForm)) {
            return "redirect:" + "/persons";
            //return showAllPersons("", model);
        } else {
            model.addAttribute("errorMessage", errorMessage);
            return "/person/editPerson";
        }
    }

    @GetMapping("/add")
    public String showAddPersonPage(Model model) {

        PersonForm personForm = new PersonForm();
        model.addAttribute("personForm", personForm);

        return "/person/addPerson";
    }

    @PostMapping("/add")
    public String addPerson(Model model,
                            @ModelAttribute("personForm") PersonForm personForm) {

        Person personDb = personService.read(personForm.getLogin());
        if (Objects.nonNull(personDb)) {
            model.addAttribute("errorMessage", personExists);
            return "/person/addPerson";
        } else {
            personService.create(personForm);
            return "redirect:" + "/person/personList";
        }
    }
}

