package com.bouncer77.springbootapp1.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 07.09.2020
 */

@SuppressWarnings("PMD")
@Entity
@Table(name = "book_step")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class BookStep {

    @EmbeddedId
    private BookStepPk id;

    @ManyToOne
    @MapsId("personId")
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private Book book;

    @NonNull
    private int page;

    private String comment;
}
