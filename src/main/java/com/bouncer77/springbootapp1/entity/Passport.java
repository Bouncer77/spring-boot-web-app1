package com.bouncer77.springbootapp1.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 07.09.2020
 */

@NoArgsConstructor
@Data
@Entity
@Table(name = "passports")
public class Passport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;


    //@Column(unique = true, nullable = false)
    private String series;

    // TODO seria + number is unique
    //@Column(unique = true, nullable = false)
    private String number;

    @OneToOne(mappedBy = "passport", cascade = CascadeType.ALL)
    private Person person;

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
