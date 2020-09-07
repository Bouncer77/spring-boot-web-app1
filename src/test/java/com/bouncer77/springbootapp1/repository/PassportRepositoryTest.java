package com.bouncer77.springbootapp1.repository;

import com.bouncer77.springbootapp1.entity.Passport;
import com.bouncer77.springbootapp1.entity.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PassportRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PassportRepository passportRepository;

    @Test
    public void deletePassport() {
        Passport passport = passportRepository.findByNumber("456555");
        passportRepository.delete(passport);


        List<Person> personList = personRepository.findAll();

        assertThat(personList).hasSize(2);
    }
}