package com.bouncer77.springbootapp1.form;

import com.bouncer77.springbootapp1.entity.Role;
import com.bouncer77.springbootapp1.util.Colour;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
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

    @NotBlank(message = "Name is mandatory")
    private String name;
    private String surname;
    private Set<Role> roles = new HashSet<>();

    public PersonForm() {
    }

    public PersonForm(String login, String email, String name, String surname) {
        this.login = login;
        this.email = email;
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return Colour.red("PersonForm{" +
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
