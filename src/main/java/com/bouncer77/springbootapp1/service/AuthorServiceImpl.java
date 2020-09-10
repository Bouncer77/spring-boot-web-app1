package com.bouncer77.springbootapp1.service;

import com.bouncer77.springbootapp1.entity.Author;
import com.bouncer77.springbootapp1.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 10.09.2020
 */

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;


    @Override
    public void create(Author author) {
        authorRepository.save(author);
    }

    @Override
    public List<Author> readAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author read(Long id) {
        Optional<Author> authorRepOptional = authorRepository.findById(id);
        return authorRepOptional.orElse(null);
    }

    @Override
    public boolean update(Author author) {

        Optional<Author> authorRepOptional = authorRepository.findById(author.getId());
        if (authorRepOptional.isPresent()) {
            authorRepository.save(author);
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {

        Optional<Author> authorRepOptional = authorRepository.findById(id);
        System.out.println(authorRepOptional.get().getSurname());
        authorRepOptional.ifPresent(author -> authorRepository.delete(author));
        return true;
    }
}
