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
@Table(name = "course")
@Getter
@Setter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @ManyToMany
    @JoinTable(name = "rcourse_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "rcourse_id")
    )
    private Set<ReadingCourse> readingCourses = new HashSet<>();

    /*@ManyToMany(mappedBy = "courses")
    Set<InstanceCourse> instanceCourses = new HashSet<>();*/

    protected Course() {
    }

    public Course(String name) {
        this.name = name;
    }
}
