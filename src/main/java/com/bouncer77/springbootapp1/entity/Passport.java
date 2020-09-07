package com.bouncer77.springbootapp1.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 07.09.2020
 */

@Entity
@Table(name = "passports")
@Getter
@Setter
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    //@Column(unique = true, nullable = false)
    private String series;

    // TODO seria + number is unique
    @Column(unique = true, nullable = false)
    private String number;

    @OneToOne(mappedBy = "passport", cascade = CascadeType.ALL)
    private Person person;


    protected Passport() {
    }

    public Passport(String series, String number) {
        this.series = series;
        this.number = number;
    }

    @Override
    public String toString() {
        return "\n\tPassport{" +
                "id=" + id +
                ", series='" + series + '\'' +
                ", number='" + number + '\'' +
                ", person=" + person +
                '}';
    }
}
