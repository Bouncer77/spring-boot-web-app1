package com.bouncer77.springbootapp1.config;

import com.bouncer77.springbootapp1.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 03.09.2020
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PersonServiceImpl personServiceImpl;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                    .authorizeRequests()

                    // Сайт с авторизацией
                    /*.antMatchers("/", "/login", "/addPerson", "/authors/**").permitAll()
                    .antMatchers("/*.css", "/images/bouncer77.png").permitAll()*/

                    // Postman (без авторизации) + сайт без авторизации
                    .antMatchers("/**").permitAll()

                    //.antMatchers("/*.css", "/images/bouncer77.png").hasRole("STUDENT")
                    //.antMatchers("/**").hasRole("ADMIN")
                    //.antMatchers("/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                    .logout()
                    .permitAll()//; // Сайт с авторизацией

                // Postman (без авторизации)
                // Для работы POSTMAN-а, при этом отваливается авторизация на сайте
                .and()
                    .csrf().disable()//; // содержит уязвимости
                    .formLogin().disable(); // отключает форму авторизации по умолчанию
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personServiceImpl)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}

