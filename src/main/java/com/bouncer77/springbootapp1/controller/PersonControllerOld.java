package com.bouncer77.springbootapp1.controller;

import com.bouncer77.springbootapp1.entity.Person;
import com.bouncer77.springbootapp1.form.PersonForm;
import com.bouncer77.springbootapp1.service.PersonServiceOld;
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
@RequestMapping("/persons")
public class PersonControllerOld {

    @Autowired
    PersonServiceOld personServiceOld;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PersonForm personForm) {

        Person person = new Person(personForm.getLogin(), personForm.getEmail(), personForm.getPassword(),
                personForm.getName(), personForm.getSurname());
        person.setActive(true);
        personServiceOld.create(person);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Person>> read() {

        final List<Person> persons = personServiceOld.readAll();

        List<Person> personsCopy = new ArrayList<>(persons);
        personsCopy.forEach(person -> {
            person.setBookSteps(null);
            person.setPassport(null);
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

        final Person person = personServiceOld.read(id);

        if (Objects.nonNull(person)) {
            Person personCopy = new Person(person);
            return new ResponseEntity<>(personCopy, HttpStatus.OK); // 200 OK
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") long id, @RequestBody PersonForm personForm) {

        final boolean updated = personServiceOld.update(id, personForm);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {

        final boolean deleted = personServiceOld.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
