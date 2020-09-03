package com.bouncer77.springbootapp1.dao;

import com.bouncer77.springbootapp1.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 03.09.2020
 */

public interface PersonDao extends JpaRepository<Person, Integer> {
    Person findByLogin(String login);

    @Query("select p from Person p order by p.name")
    List<Person> findAllOrderedByFirstName();
}
