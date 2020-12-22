package com.bouncer77.springbootapp1.controller;

import com.bouncer77.springbootapp1.entity.Person;
import com.bouncer77.springbootapp1.entity.Role;
import com.bouncer77.springbootapp1.form.PersonForm;
import com.bouncer77.springbootapp1.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 28.09.2020
 */

@Controller
@RequiredArgsConstructor
public class WebAnonymousController {

    private final PersonService personService;

    @Value("${error.message}")
    String errorMessage;

    @Value("${error.message.person.exists}")
    String personExists;

    @GetMapping("/registration")
    public String registrationPersonGet(Model model) {

        PersonForm personForm = new PersonForm();
        model.addAttribute("personForm", personForm);
        return "/registration";
    }

    @PostMapping("/registration")
    public String registrationPersonPost(Model model,
                                         @ModelAttribute("personForm") PersonForm personForm) {

        Person personDb = personService.read(personForm.getLogin());
        if (Objects.nonNull(personDb)) {
            System.out.println(personDb.toString());
            model.addAttribute("errorMessage", personExists);
            return "/registration";
        } else {
            personForm.getRoles().add(Role.STUDENT);
            personService.create(personForm);
            return "redirect:" + "/profile";
        }
    }
}
