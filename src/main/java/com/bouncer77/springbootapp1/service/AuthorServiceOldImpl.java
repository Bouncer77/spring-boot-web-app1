package com.bouncer77.springbootapp1.service;

import com.bouncer77.springbootapp1.entity.Author;
import com.bouncer77.springbootapp1.form.AuthorForm;
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
public class AuthorServiceOldImpl implements AuthorServiceOld {

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
    public Author read(long id) {
        Optional<Author> authorRepOptional = authorRepository.findById(id);
        return authorRepOptional.orElse(null);
    }

    @Override
    public boolean update(long id, AuthorForm authorForm) {

        Optional<Author> authorRepOptional = authorRepository.findById(id);
        if (authorRepOptional.isPresent()) {
            Author author = authorRepOptional.get();
            author.setName(authorForm.getName());
            author.setSurname(authorForm.getSurname());
            authorRepository.save(author);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(long id) {

        Optional<Author> authorRepOptional = authorRepository.findById(id);
        if (authorRepOptional.isPresent()) {
            authorRepository.delete(authorRepOptional.get());
            return true;
        }

        return false;
    }
}
