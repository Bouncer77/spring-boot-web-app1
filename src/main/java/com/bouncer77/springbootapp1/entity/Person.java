package com.bouncer77.springbootapp1.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Простой JavaBean объект представляет Пользователя
 *
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 27.08.2020
 */

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique=true)
    private String login;

    @Column(name = "password")
    private String password;

    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "person_roles", joinColumns = @JoinColumn(name = "person_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    /*@Transient
    private String confirmPassword;*/

    /*@ManyToMany
    @JoinTable(name = "person_role",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;*/

    @Column(unique=true)
    private String email;

    private String surname;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private List<Phone> phones;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "passport_id")
    private Passport passport;

    protected Person() {}

    public Person(String login, String email, String password, String name, String surname) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phone) {
        this.phones = phone;
    }

    @Override
    public String toString() {
        return "\nUser{" +
                "id=" + id +
                ", login='" + login + '\'' +
                //", passport='" + passport + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                //", passport='" + passport + '\'' +
                ", phones='" + phones + '\'' +
                '}';
    }

/*    @Override
    public String toString() {
        return "\nPerson{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", roles=" + roles +
                ", email='" + email + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", passport=" + passport +
                '}';
    }*/
}
