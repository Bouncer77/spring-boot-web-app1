package com.bouncer77.springbootapp1.service;

import com.bouncer77.springbootapp1.entity.Person;
import com.bouncer77.springbootapp1.form.PersonForm;
import com.bouncer77.springbootapp1.repository.PersonRepositoryOld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 10.09.2020
 */

@Service
public class PersonServiceOldImpl implements PersonServiceOld {

    @Autowired
    PersonRepositoryOld personRepositoryOld;

    @Override
    public void create(Person person) {
        personRepositoryOld.save(person);
    }

    @Override
    public List<Person> readAll() {
        return personRepositoryOld.findAll();
    }

    @Override
    public Person read(long id) {
        Optional<Person> personRepOptional = personRepositoryOld.findById(id);
        return personRepOptional.orElse(null);
    }

    @Override
    public boolean update(long id, PersonForm personForm) {

        Optional<Person> personRepOptional = personRepositoryOld.findById(id);
        if (personRepOptional.isPresent()) {
            Person person = personRepOptional.get();
            /*// Меняет только имя и фамилию
            person.setName(personForm.getName());
            person.setSurname(personForm.getSurname());*/
            person.setParams(personForm);
            personRepositoryOld.save(person);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(long id) {

        Optional<Person> personRepOptional = personRepositoryOld.findById(id);
        if (personRepOptional.isPresent()) {
            personRepositoryOld.delete(personRepOptional.get());
            return true;
        }

        return false;
    }
}
