package com.bouncer77.springbootapp1.repository;

import com.bouncer77.springbootapp1.entity.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 11.09.2020
 */

@NoRepositoryBean
public interface CommonRepository<E extends AbstractEntity> extends CrudRepository<E, Long> {
}
