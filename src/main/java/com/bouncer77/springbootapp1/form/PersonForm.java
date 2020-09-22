package com.bouncer77.springbootapp1.form;

import com.bouncer77.springbootapp1.entity.Role;
import com.bouncer77.springbootapp1.util.Colour;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 03.09.2020
 */

@Getter
@Setter
public class PersonForm {

    private String login;
    private String email;
    private String password;
    private String confirmPassword;
    private String name;
    private String surname;
    private List<String> roleList = new ArrayList<>();
    private Set<Role> roles = new HashSet<>();
    private Boolean[] rolesMas = new Boolean[4];

    public PersonForm() {}

    public PersonForm(String login, String email, String name, String surname) {
        this.login = login;
        this.email = email;
        this.name = name;
        this.surname = surname;
    }

    /*public void addPersonRole(Role role) {
        this.roles.add(role);
    }*/

    @Override
    public String toString() {
        return Colour.red( "PersonForm{" +
                "login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", roles=" + roles +
                '}');
    }
}
