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
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    // 1 -
    /*@ManyToMany
    @JoinTable(name = "rcourse_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "rcourse_id")
    )
    private Set<ReadingCourse> readingCourses = new HashSet<>();*/

    // 2 +
    @ManyToMany(mappedBy = "courses")
    private Set<ReadingCourse> readingCourses = new HashSet<>();

    @ManyToMany(mappedBy = "courses")
    Set<InstanceCourse> instanceCourses = new HashSet<>();

    public Course(String name) {
        this.name = name;
    }
}
