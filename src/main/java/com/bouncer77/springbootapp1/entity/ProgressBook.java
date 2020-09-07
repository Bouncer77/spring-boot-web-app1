package com.bouncer77.springbootapp1.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.jmx.export.annotation.ManagedAttribute;

import javax.persistence.*;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 07.09.2020
 */

@SuppressWarnings("PMD")
@Entity
@Table(name = "progress_book")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ProgressBook {

    @EmbeddedId
    private ProgressBookKey id;

    @ManyToOne
    @MapsId("personId")
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private Book book;

    // int page;
}
