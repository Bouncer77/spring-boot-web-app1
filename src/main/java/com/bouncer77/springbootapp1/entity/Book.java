package com.bouncer77.springbootapp1.entity;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 07.09.2020
 */

@Entity
@Table(name = "book")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private Integer lastPage;

    @ManyToMany
    @JoinTable(
            name = "author_book",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors;

    @ManyToMany
    @JoinTable(
            name = "tag_book",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Author> tags;

    protected Book() {
    }

    public Book(String name, Integer lastPage) {
        this.name = name;
        this.lastPage = lastPage;
    }

}
