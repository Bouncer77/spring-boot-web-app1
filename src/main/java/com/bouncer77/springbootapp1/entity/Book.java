package com.bouncer77.springbootapp1.entity;

import lombok.Data;
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
@Table(name = "book")
// @Data
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NonNull
    private String name;

    @NonNull
    private Integer lastPage;

    @ManyToMany
    @JoinTable(
            name = "author_book",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "tag_book",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @NonNull
    private Set<Author> tags = new HashSet<>();

    protected Book() {
    }

    public Book(String name, Integer lastPage, Set<Author> authors) {
        this.name = name;
        this.lastPage = lastPage;
        this.authors = authors;
    }

    public Book(String name, Integer lastPage, Author author) {
        this.name = name;
        this.lastPage = lastPage;
        this.authors.add(author);
    }

}
