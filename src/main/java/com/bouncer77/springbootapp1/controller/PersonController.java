package com.bouncer77.springbootapp1.controller;

import com.bouncer77.springbootapp1.dao.PersonDao;
import com.bouncer77.springbootapp1.form.PersonForm;
import com.bouncer77.springbootapp1.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 03.09.2020
 */

@Controller
// @RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonDao personDao;

    // Вводится (inject) из application.properties.
    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @GetMapping(value = { "/", "/index" })
    public String index(Model model) {

        model.addAttribute("message", message);

        return "index";
    }

    @GetMapping("/personList")
    public String showAllPersons(Model model) {
        model.addAttribute("persons", personDao.findAll());
        return "personList";
    }

    @GetMapping("/addPerson")
    public String showAddPersonPage(Model model) {

        PersonForm personForm = new PersonForm();
        model.addAttribute("personForm", personForm);

        return "addPerson";
    }

    @PostMapping("/addPerson")
    public String savePerson(Model model,
                             @ModelAttribute("personForm") PersonForm personForm) {

        String email = personForm.getEmail();
        String password = personForm.getPassword();

        if (email != null && email.length() > 0 && email.matches("\\w+@\\w+\\.\\w+")//
                && password != null && password.length() > 0) {
            Person newPerson = new Person(email, password);
            personDao.save(newPerson);

            return "redirect:/personList";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "addPerson";
    }

}
