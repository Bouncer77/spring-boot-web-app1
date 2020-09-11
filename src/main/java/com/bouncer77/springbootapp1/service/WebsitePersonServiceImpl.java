package com.bouncer77.springbootapp1.service;

import com.bouncer77.springbootapp1.repository.PersonRepositoryOld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 08.09.2020
 */

@Service
public class WebsitePersonServiceImpl implements UserDetailsService, WebsitePersonService {

    @Autowired
    private PersonRepositoryOld personRepositoryOld;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return personRepositoryOld.findByLogin(username);
    }
}
