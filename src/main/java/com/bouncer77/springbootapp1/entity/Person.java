package com.bouncer77.springbootapp1.entity;

import com.bouncer77.springbootapp1.form.PersonForm;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Простой JavaBean объект представляет Пользователя
 *
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 27.08.2020
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "person")
public class Person implements UserDetails {

    /**
     * Person Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    /**
     * Login
     */
    @NonNull
    @Column(unique = true, nullable = false)
    private String login;

    /**
     * Password
     */
    @NonNull
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * Person status
     */
    @Builder.Default
    @Column(name = "active", nullable = false)
    private Boolean active = true;

    /**
     * Date and Time of registration person
     */
    @Builder.Default
    @Column(name = "regdatetime")
    private LocalDateTime regDateTime = LocalDateTime.now();

    @NonNull
    @Column(unique = true)
    private String email;

    @NonNull
    @Column(name = "surname", nullable = false)
    private String surname;

    @NonNull
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Roles of current person
     */
    @Singular
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "person_roles", joinColumns = @JoinColumn(name = "person_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    /**
     * Registration for the course
     */
    @Singular("instanceCourseStudent")
    @ManyToMany
    @JoinTable(name = "icourse_student",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "icourse_id")
    )
    private Set<InstanceCourse> instanceCoursesStudent;

    /**
     * Knowledge of subjects
     */
    @Singular
    @ManyToMany
    @JoinTable(name = "tag_person",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;

    @Singular("instanceCourseTeacher")
    @ManyToMany
    @JoinTable(name = "icourse_teacher",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "icourse_id")
    )
    private Set<InstanceCourse> instanceCoursesTeacher;

    @Singular
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private Set<Phone> phones;

    @Singular
    @OneToMany(mappedBy = "person")
    Set<BookStep> bookSteps;

    /**
     * Passport
     */
    @Builder.Default
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "passport_id", referencedColumnName = "id")
    private Passport passport = new Passport();

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
        return "Person{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", roles=" + roles +
                '}';
    }

    public void setParams(PersonForm personForm) {
        this.name = personForm.getName();
        this.surname = personForm.getSurname();
        this.email = personForm.getEmail();
        this.password = personForm.getPassword();
        this.login = personForm.getLogin();
        this.roles = new HashSet<>();

        for (String str : personForm.getRoles()) {
            Role en = Role.valueOf(str);
            this.roles.add(en);
        }
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

