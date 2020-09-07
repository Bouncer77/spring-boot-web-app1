package com.bouncer77.springbootapp1.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 07.09.2020
 */

@Entity
@Table(name = "icourse")
@Getter
@Setter
public class InstanceCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @ManyToMany
    @JoinTable(name = "course_icourse",
            joinColumns = @JoinColumn(name = "icourse_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses = new HashSet<>();

    @ManyToMany(mappedBy = "instanceCourses")
    Set<Person> persons = new HashSet<>();

    protected InstanceCourse() {
    }

    public InstanceCourse(String name) {
        this.name = name;
    }
}
