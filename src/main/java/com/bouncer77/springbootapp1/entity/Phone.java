package com.bouncer77.springbootapp1.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 07.09.2020
 */

@NoArgsConstructor
@Data
@Entity
@Table(name = "phone")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String number;

    public Phone(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "\n\tPhone{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }
}
