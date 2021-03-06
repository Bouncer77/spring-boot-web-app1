package com.bouncer77.springbootapp1.controller;

import com.bouncer77.springbootapp1.entity.Person;
import com.bouncer77.springbootapp1.entity.Role;
import com.bouncer77.springbootapp1.form.PersonForm;
import com.bouncer77.springbootapp1.service.PersonService;
import com.bouncer77.springbootapp1.util.Colour;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 03.09.2020
 */

@Controller
@RequestMapping("/persons")
@PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR', 'TEACHER')")
@RequiredArgsConstructor
public class WebPersonController {

    private final PersonService personService;

    @Value("${error.message}")
    String errorMessage;

    @Value("${error.message.person.exists}")
    String personExists;

    // Create
    @GetMapping("/add")
    public String addPersonGet(Model model) {

        PersonForm personForm = new PersonForm();
        model.addAttribute("personForm", personForm);
        model.addAttribute("allRoles", Role.values());

        return "/person/addPerson";
    }

    @PostMapping("/add")
    public String addPersonPost(Model model,
                                @ModelAttribute("personForm") PersonForm personForm) {

        Person personDb = personService.read(personForm.getLogin());
        if (Objects.nonNull(personDb)) {
            System.out.println(personDb.toString());
            model.addAttribute("errorMessage", personExists);
            return "/person/addPerson";
        } else {
            personService.create(personForm);
            return "redirect:" + "/";
        }
    }

    // Read
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
        return "/person/showAllPersons";
    }

    // Update
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
        model.addAttribute("roles", person.getRoles());
        model.addAttribute("person", person);
        model.addAttribute("personForm", personForm);
        model.addAttribute("id", id);
        model.addAttribute("allRoles", Role.values());

        return "/person/editPerson";
    }

    @PostMapping("/edit")
    //public String editPersonPut(@PathVariable Person person, Model model,
    public String editPersonPut(@RequestParam(name = "id") long id,
                                @Valid @ModelAttribute("personForm") PersonForm personForm,
                                Model model) {

        Person person = personService.read(id);

        if (Objects.nonNull(person.getRoles())) {
            System.out.println(Colour.green("Roles: " + person.getRoles()));
        } else {
            System.out.println(Colour.red("Roles is null : " + person));
        }

        model.addAttribute("personForm", personForm);
        model.addAttribute("id", id);
        model.addAttribute("allRoles", Role.values());
        if (personService.update(person.getId(), personForm)) {
            return "redirect:" + "/persons";
        } else {
            model.addAttribute("errorMessage", errorMessage);
            return "/person/editPerson";
        }
    }

    // Delete
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

        //return "redirect:" + "/persons"; // не выводит удаленного
        // return "/person/showAllPersons"; // не выводит всех остальных
        return showAllPersons("", model); // /persons/delete?id=2 - не применяет css к таблице

    }
}

