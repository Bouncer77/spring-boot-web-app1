package com.bouncer77.springbootapp1.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Простой JavaBean объект представляет роль {@link com.bouncer77.springbootapp1.model.Person}
 * Принимаемые значения: Студент, Преподаватель, Админ
 *
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 27.08.2020
 * @version 1.0
 */

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public Role() {}

    /*@ManyToMany(mappedBy = "roles")
    private Set<Person> people;

    public Role() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Person> getUsers() {
        return people;
    }

    public void setUsers(Set<Person> personSet) {
        this.people = personSet;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userSet=" + people +
                '}';
    }*/
}
