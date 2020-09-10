package com.bouncer77.springbootapp1.repository;

import com.bouncer77.springbootapp1.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 07.09.2020
 */

public interface AuthorRepository extends JpaRepository<Author, Long> {

    // Author findById(Long id);

    Author findBySurname(String surname);
}
