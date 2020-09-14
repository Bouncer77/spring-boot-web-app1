package com.bouncer77.springbootapp1.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 07.09.2020
 */

@NoArgsConstructor
@Data
@Entity
@Table(name = "icourse")
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

    @ManyToMany(mappedBy = "instanceCoursesStudent")
    Set<Person> students = new HashSet<>();

    @ManyToMany(mappedBy = "instanceCoursesTeacher")
    Set<Person> teachers = new HashSet<>();

    public InstanceCourse(String name) {
        this.name = name;
    }
}
