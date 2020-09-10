package com.bouncer77.springbootapp1.form;

import lombok.Getter;
import lombok.Setter;

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

    public PersonForm() {}

    public PersonForm(String login, String email, String name, String surname) {
        this.login = login;
        this.email = email;
        this.name = name;
        this.surname = surname;
    }
}
