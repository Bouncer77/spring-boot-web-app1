package com.bouncer77.springbootapp1.entity;

import javax.persistence.*;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 07.09.2020
 */

@Entity
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    //@Column(unique = true, nullable = false)
    private String series;

    // TODO seria + number is unique
    @Column(unique = true, nullable = false)
    private String number;

    @OneToOne(mappedBy = "passport", cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private Person person;


    protected Passport() {
    }

    public Passport(String series, String number) {
        this.series = series;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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
