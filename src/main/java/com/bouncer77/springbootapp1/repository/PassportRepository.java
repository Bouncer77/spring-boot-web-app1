package com.bouncer77.springbootapp1.repository;

import com.bouncer77.springbootapp1.entity.Passport;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 07.09.2020
 */

public interface PassportRepository extends JpaRepository<Passport, Integer> {

    Passport findBySeriesAndNumber(String series, String number);
}
