package com.bouncer77.springbootapp1.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Простой JavaBean объект представляет Пользователя
 *
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 27.08.2020
 */

@Entity
@Table(name = "persons")
@Getter
@Setter
public class Person implements UserDetails {

    /**
     * Person Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    /**
     * Passport
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "passport_id", referencedColumnName = "id")
    private Passport passport;


    /**
     * Login
     */
    @Column(unique = true, nullable = false)
    private String login;

    /**
     * Password
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * Person status
     */
    @Column(name = "active", nullable = false)
    private boolean active = true;

    /**
     * Roles of current person
     */
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "person_roles", joinColumns = @JoinColumn(name = "person_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    /**
     * Registration for the course
     */
    @ManyToMany
    @JoinTable(name = "icourse_student",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "icourse_id")
    )
    private Set<InstanceCourse> instanceCoursesStudent = new HashSet<>();

    /**
     * Knowledge of subjects
     */
    @ManyToMany
    @JoinTable(name = "tag_person",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "icourse_teacher",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "icourse_id")
    )
    private Set<InstanceCourse> instanceCoursesTeacher = new HashSet<>();

    /**
     * Date and Time of registration person
     */
    @Column(name = "regdatetime")
    private LocalDateTime regDateTime = LocalDateTime.now();

    @Column(unique = true)
    private String email;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private Set<Phone> phones;

    @OneToMany(mappedBy = "person")
    Set<BookStep> bookSteps;

    protected Person() {
    }

    public Person(String login, String email, String password, String name, String surname) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.regDateTime = LocalDateTime.now();
    }

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

    @Override
    public String toString() {
        return "\nUser{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phones='" + phones + '\'' +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }
}
