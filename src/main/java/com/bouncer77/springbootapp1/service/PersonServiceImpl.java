package com.bouncer77.springbootapp1.service;

import com.bouncer77.springbootapp1.aspects.LogExecutionTime;
import com.bouncer77.springbootapp1.entity.Person;
import com.bouncer77.springbootapp1.entity.Role;
import com.bouncer77.springbootapp1.form.PersonForm;
import com.bouncer77.springbootapp1.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 10.09.2020
 */

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void create(PersonForm personForm) {

        String login = personForm.getLogin();
        String email = personForm.getEmail();
        String password = personForm.getPassword();
        String name = personForm.getName();
        String surname = personForm.getSurname();

        if (email != null && email.length() > 0 && email.matches("\\w+@\\w+\\.\\w+")//
                && password != null && password.length() > 0) {

            //Person person = new Person(login, email, password, name, surname);
            Person person = Person.builder()
                    .login(login)
                    .email(email)
                    .password(password)
                    .name(name)
                    .surname(surname)
                    .build();

            person.setActive(true);
            person.setRoles(Collections.singleton(Role.STUDENT));

            // Шифрование пароля
            person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));

            this.create(person);
        }
    }

    @Override
    public void create(Person person) {
        personRepository.save(person);
    }

    @Override
    @LogExecutionTime
    public List<Person> readAll() {
        return personRepository.findAll();
    }

    @Override
    public Person read(long id) {
        Optional<Person> personRepOptional = personRepository.findById(id);
        return personRepOptional.orElse(null);
    }

    public Person read(String login) {
        return personRepository.findByLogin(login);
    }

    @Override
    public List<Person> readsByName(String name) {
        return personRepository.findByName(name);
    }

    @Override
    public boolean update(long id, PersonForm personForm) {

        // Если пароли не совпали
        if (!personForm.getPassword().equals(personForm.getConfirmPassword())) {
            return false;
        } else {
            // Шифрование пароля
            personForm.setPassword(bCryptPasswordEncoder.encode(personForm.getPassword()));

            Optional<Person> personRepOptional = personRepository.findById(id);
            if (personRepOptional.isPresent()) {
                Person person = personRepOptional.get();
                person.setParams(personForm);
                personRepository.save(person);
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public boolean delete(long id) {

        Optional<Person> personRepOptional = personRepository.findById(id);
        if (personRepOptional.isPresent()) {
            personRepository.delete(personRepOptional.get());
            return true;
        }

        return false;
    }
}
