package com.bouncer77.springbootapp1.service;

import com.bouncer77.springbootapp1.entity.Person;
import com.bouncer77.springbootapp1.form.PersonForm;

import java.util.List;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 10.09.2020
 */

public interface PersonService extends EntityCrudService<Person, PersonForm> {

    /**
     * Возвращает пользователя по его login
     *
     * @param login - login пользователя
     * @return - пользователь с login
     */
    Person read(String login);

    List<Person> readsByName(String name);

    void create(PersonForm personForm);
}
