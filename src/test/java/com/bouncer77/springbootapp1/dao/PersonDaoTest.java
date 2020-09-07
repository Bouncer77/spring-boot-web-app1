package com.bouncer77.springbootapp1.dao;

import com.bouncer77.springbootapp1.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PersonDaoTest {

    @Autowired
    PersonDao personDao;

    @Test
    void findByName() {
        List<Person> personList = personDao.findByName("Anna");

        assertThat(personList).hasSize(1);
        assertThat(personList.get(0).getName()).isEqualTo("Anna");
        assertThat(personList.get(0).getSurname()).isEqualTo("Goncharova");
    }

    @Test
    void findAllOrderedByFirstName() {
        List<Person> personList = personDao.findAllOrderedByFirstName();
        assertThat(personList).hasSize(3);
        assertThat(personList.get(2).getSurname()).isEqualTo("Ivanov");
    }
}