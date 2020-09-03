package com.bouncer77.springbootapp1.controller;

import com.bouncer77.springbootapp1.dao.PersonDao;
import com.bouncer77.springbootapp1.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 03.09.2020
 */

@Controller
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonDao personDao;

    @GetMapping("/name")
    public String showAllPersons(Model model) {
        model.addAttribute("persons", personDao.findAll());
        return "allperson";
    }

}
