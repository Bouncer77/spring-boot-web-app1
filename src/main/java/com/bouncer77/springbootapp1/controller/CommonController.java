package com.bouncer77.springbootapp1.controller;

import com.bouncer77.springbootapp1.entity.AbstractEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 11.09.2020
 */

public interface CommonController<E extends AbstractEntity> {

    @PostMapping
    ResponseEntity<E> save(@RequestBody E entity);


}
