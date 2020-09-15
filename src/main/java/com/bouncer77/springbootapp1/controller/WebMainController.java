package com.bouncer77.springbootapp1.controller;

import com.bouncer77.springbootapp1.entity.Person;
import com.bouncer77.springbootapp1.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Objects;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 15.09.2020
 */
@Controller
public class WebMainController {

    @Autowired
    private PersonService personService;

    @Value("${welcome.message}")
    private String message;

    @GetMapping(value = {"/", "/index"})
    public String index(Authentication authentication, Model model) {

        model.addAttribute("message", message);
        // вывести логин-ссылку на страницу профиля
        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Person person = personService.read(userDetails.getUsername());
            if (Objects.nonNull(person)) {
                model.addAttribute("personId", person.getId());
            }
        }

        return "main";
    }

    @GetMapping("/about")
    public String showAbout(Authentication authentication) {
        return "/about";
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
