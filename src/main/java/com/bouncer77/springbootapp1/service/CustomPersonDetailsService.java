package com.bouncer77.springbootapp1.service;

import com.bouncer77.springbootapp1.entity.Person;
import com.bouncer77.springbootapp1.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 08.09.2020
 */

@Service
public class CustomPersonDetailsService implements UserDetailsService, WebsitePersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Person person = personRepository.findByLogin(username);
        if (Objects.isNull(person)) {
            throw new UsernameNotFoundException("Unknown user: " + username);
        }

        List<String> strRoles = new ArrayList<>();
        person.getRoles().forEach(role -> {
            strRoles.add(role.name());
        });
        String[] arrList = strRoles.toArray(new String[0]);

        return User.builder()
                .username(person.getLogin())
                .password(person.getPassword())
                .roles(arrList)
                .build();
        //return personRepository.findByLogin(username);
    }
}
