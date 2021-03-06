package com.bouncer77.springbootapp1.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 07.09.2020
 */

@NoArgsConstructor
@Data
@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NonNull
    private String name;

    private String description;

    @Transient
    @ManyToMany(mappedBy = "tags")
    private Set<Book> books = new HashSet<>();

    @Transient
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "tags")
    private Set<Person> persons;

    public Tag(String name) {
        this.name = name;
    }

    public Tag(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
