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

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private final String TEST_LOGIN_1 = "TestLogin1";
    private final String TEST_LOGIN_2 = "TestLogin2";

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

        Person testPerson2 = Person.builder()
                .active(true)
                .name("Test")
                .surname("Testov2")
                .role(Role.STUDENT)
                .email("test2@gmail.com")
                .login(TEST_LOGIN_2)
                .password(passwordEncoder.encode("123"))
                .phone(new Phone("89681110909"))
                .passport(new Passport("888", "123322"))
                .build();

        personRepository.save(testPerson1);
        personRepository.save(testPerson2);
    }

    @AfterEach
    void afterAllTest() {
        Person person1 = personRepository.findByLogin(TEST_LOGIN_1);
        personRepository.delete(person1);

        Person person2 = personRepository.findByLogin(TEST_LOGIN_2);
        personRepository.delete(person2);
    }

    @Test
    void findByLogin() {

        Person test1 = personRepository.findByLogin(TEST_LOGIN_1);

        assertThat(test1.getName()).isEqualTo("Test");
        assertThat(test1.getSurname()).isEqualTo("Testov1");
    }

    @Test
    void findByName() {

        List<Person> persons = personRepository.findByName("Test");

        assertThat(persons).hasSize(2);
        assertThat(persons.get(0).getName()).isEqualTo("Test");
        assertThat(persons.get(1).getName()).isEqualTo("Test");
    }
}