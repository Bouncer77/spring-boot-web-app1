package com.bouncer77.springbootapp1.service;

import com.bouncer77.springbootapp1.entity.Person;
import com.bouncer77.springbootapp1.repository.PersonRepositoryOld;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 11.09.2020
 */

@Service
public class PersonService extends AbstractService<Person, PersonRepository> {

    public PersonService(PersonRepositoryOld repository) {
        super(repository);
    }

    @Override
    public void save(Person entity) {

    }

    @Override
    public List<Person> readAll() {
        return null;
    }

    @Override
    public Person read(long id) {
        return null;
    }

    @Override
    public boolean update(Person entity) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }
}
