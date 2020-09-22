package com.bouncer77.springbootapp1.repository;

import com.bouncer77.springbootapp1.entity.Passport;
import com.bouncer77.springbootapp1.entity.Person;
import com.bouncer77.springbootapp1.entity.Phone;
import com.bouncer77.springbootapp1.entity.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PassportRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PassportRepository passportRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private final String TEST_LOGIN_1 = "TestLogin1";

    @BeforeEach
    void beforeAllTests() {
        Person testPerson1 = Person.builder()
                .active(true)
                .name("Test")
                .surname("Testov1")
                .roles(Arrays.asList(Role.STUDENT, Role.TEACHER))
                .email("test1@gmail.com")
                .login(TEST_LOGIN_1)
                .password(passwordEncoder.encode("123"))
                .phone(new Phone("89681119999"))
                .passport(new Passport("777", "123321"))
                .build();

        personRepository.save(testPerson1);
    }

    @AfterEach
    void afterAllTest() {
        Person person1 = personRepository.findByLogin(TEST_LOGIN_1);
        if (Objects.nonNull(person1))
            personRepository.delete(person1);
    }

    @Test
    public void deleteCascadePassport() {
        // Проверка на каскадное удаление
        List<Person> people = personRepository.findAll();
        List<Passport> passports = passportRepository.findAll();
        assertThat(people.size()).isEqualTo(passports.size());
        int peopleCountBefore = people.size();

        Passport passport = passportRepository.findBySeriesAndNumber("777", "123321");
        passportRepository.delete(passport);

        passports = passportRepository.findAll();
        assertThat(passports.size()).isEqualTo(peopleCountBefore - 1);
    }
}