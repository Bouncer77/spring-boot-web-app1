package com.bouncer77.springbootapp1.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 07.09.2020
 */

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class ProgressBookKey implements Serializable {

    @Column(name = "person_id")
    private Long personId;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "reg_date")
    private LocalDate regDate = LocalDate.now();

    protected ProgressBookKey() {
    }
}
