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
@Table(name = "rcourse")
@Getter
@Setter
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

    /*@ManyToMany(mappedBy = "readingCourses")
    Set<InstanceCourse> instanceCourses = new HashSet<>();*/

    protected ReadingCourse() {
    }

    public ReadingCourse(String name) {
        this.name = name;
    }
}
