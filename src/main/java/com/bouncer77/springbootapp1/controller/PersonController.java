package com.bouncer77.springbootapp1.controller;

import com.bouncer77.springbootapp1.entity.Person;
import com.bouncer77.springbootapp1.form.PersonForm;
import com.bouncer77.springbootapp1.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 10.09.2020
 */

@RestController
@RequestMapping("/json/persons")
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PersonForm personForm) {

        personService.create(personForm);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Person>> read() {

        final List<Person> persons = personService.readAll();

        List<Person> personsCopy = new ArrayList<>(persons);
        personsCopy.forEach(person -> {
            person.setBookSteps(null);
            person.setInstanceCoursesStudent(null);
            person.setInstanceCoursesTeacher(null);
            person.setPhones(null);
            person.setTags(null);
        });

        return !personsCopy.isEmpty()
                ? new ResponseEntity<>(persons, HttpStatus.OK) // 200 OK
                : new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Person> read(@PathVariable(name = "id") long id) {

        final Person person = personService.read(id);

        if (Objects.nonNull(person)) {
            //TODO проверка Builder
            //Person personCopy = new Person(person);
            Person personCopy = new Person();
            return new ResponseEntity<>(personCopy, HttpStatus.OK); // 200 OK
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") long id, @RequestBody PersonForm personForm) {

        final boolean updated = personService.update(id, personForm);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {

        final boolean deleted = personService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
