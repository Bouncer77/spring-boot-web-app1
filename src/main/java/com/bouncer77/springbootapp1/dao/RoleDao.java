package com.bouncer77.springbootapp1.dao;

import com.bouncer77.springbootapp1.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 03.09.2020
 */

public interface RoleDao extends JpaRepository<Role, Integer> {
}
