package com.bouncer77.springbootapp1.controller;

import com.bouncer77.springbootapp1.entity.Author;
import com.bouncer77.springbootapp1.form.AuthorForm;
import com.bouncer77.springbootapp1.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 09.09.2020
 */

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AuthorForm authorForm) {

        Author author = new Author(authorForm.getName(), authorForm.getSurname());
        authorService.create(author);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Author>> read() {

        final List<Author> authors = authorService.readAll();

        List<Author> authorsCopy = new ArrayList<>(authors);
        /*authorsCopy.forEach(author -> {
            author.setBooks(null);
        });*/

        return !authorsCopy.isEmpty()
                ? new ResponseEntity<>(authors, HttpStatus.OK) // 200 OK
                : new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Author> read(@PathVariable(name = "id") long id) {

        final Author author = authorService.read(id);

        return author != null
                ? new ResponseEntity<>(author, HttpStatus.OK) // 200 OK
                : new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") long id, @RequestBody AuthorForm authorForm) {

        final boolean updated = authorService.update(id, authorForm);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {

        final boolean deleted = authorService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
