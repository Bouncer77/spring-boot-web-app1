package com.bouncer77.springbootapp1.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 07.09.2020
 */

@NoArgsConstructor
@Data
@Embeddable
public class BookStepPk implements Serializable {

    @Column(name = "person_id")
    private Long personId;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "reg_date")
    private LocalDate regDate = LocalDate.now();
}
