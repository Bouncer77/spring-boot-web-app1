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
@Table(name = "rcourse")
public class ReadingCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @ManyToMany
    @JoinTable(name = "book_rcourse",
            joinColumns = @JoinColumn(name = "rcourse_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> books = new HashSet<>();

    // 2 +
    @ManyToMany
    @JoinTable(name = "rcourse_course",
            joinColumns = @JoinColumn(name = "rcourse_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    Set<InstanceCourse> courses = new HashSet<>();

    // 1 -
    /*@ManyToMany(mappedBy = "readingCourses")
    Set<InstanceCourse> instanceCourses = new HashSet<>();*/

    public ReadingCourse(String name) {
        this.name = name;
    }
}
