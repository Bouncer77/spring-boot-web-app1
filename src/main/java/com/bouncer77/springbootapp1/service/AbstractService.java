package com.bouncer77.springbootapp1.service;

import com.bouncer77.springbootapp1.entity.AbstractEntity;
import com.bouncer77.springbootapp1.repository.CommonRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 11.09.2020
 */

public abstract class AbstractService<E extends AbstractEntity, R extends CommonRepository<E>>
        implements CommonService<E> {

    protected final R repository;

    @Autowired
    public AbstractService(R repository) {
        this.repository = repository;
    }

//другие методы, переопределённые из интерфейса
}
