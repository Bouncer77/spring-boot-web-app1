package com.bouncer77.springbootapp1.config;

import com.bouncer77.springbootapp1.service.CustomPersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 03.09.2020
 */

@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    //TODO прикрепить DI через интерфейс (DI через интерфейс не работает)
    @Autowired
    CustomPersonDetailsService personDetailsService;


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                    .authorizeRequests()

                // Сайт с авторизацией
                .antMatchers("/web/books", "/personList", "/web/books/**").hasAnyRole("ADMIN, TEACHER, MODERATOR")
                .antMatchers("/addPerson").anonymous()
                .antMatchers("/deletePerson", "/editPerson*", "/persons/**").hasRole("MODERATOR")
                .antMatchers("/", "/index", "/applications", "/contact", "/about",
                "/js/**", "/images/**", "/css/**").permitAll()
                .antMatchers("/**").hasRole("ADMIN")



                    // Postman (без авторизации) + сайт без авторизации
                    //.antMatchers("/**").permitAll()

                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                    .logout()
                    .permitAll(); // Сайт с авторизацией

                // Postman (без авторизации)
                // Для работы POSTMAN-а
                /*.and()
                    .csrf().disable()//; // содержит уязвимости
                    .formLogin().disable(); // отключает форму авторизации по умолчанию*/
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailsService)
                .passwordEncoder(bCryptPasswordEncoder());
                //.passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}

