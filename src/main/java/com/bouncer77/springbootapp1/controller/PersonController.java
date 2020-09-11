package com.bouncer77.springbootapp1.controller;

import com.bouncer77.springbootapp1.entity.Person;
import com.bouncer77.springbootapp1.service.PersonService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 11.09.2020
 */

@RestController
@RequestMapping("/persons")
public class PersonController extends AbstractController<Person, PersonService> {

    public PersonController(PersonService service) {
        super(service);
    }
}
