package com.bouncer77.springbootapp1.controller;

import com.bouncer77.springbootapp1.entity.Book;
import com.bouncer77.springbootapp1.form.BookForm;
import com.bouncer77.springbootapp1.form.BooksCreationDto;
import com.bouncer77.springbootapp1.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 11.09.2020
 */

@RestController
@RequestMapping("/json/books")
public class BookController {

    @Autowired
    BookService bookService;

    public ResponseEntity<?> create(@RequestBody BooksCreationDto booksCreationDto) {

        for (Book book : booksCreationDto.getBooks()) {
            bookService.create(book);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody BookForm bookForm) {

        bookService.create(bookForm);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Book>> read() {

        final List<Book> books = bookService.readAll();

        List<Book> booksCopy = new ArrayList<>(books);
        /*booksCopy.forEach(book -> {
            book.setBooks(null);
        });*/

        return !booksCopy.isEmpty()
                ? new ResponseEntity<>(books, HttpStatus.OK) // 200 OK
                : new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Book> read(@PathVariable(name = "id") long id) {

        final Book book = bookService.read(id);

        return book != null
                ? new ResponseEntity<>(book, HttpStatus.OK) // 200 OK
                : new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") long id, @RequestBody BookForm bookForm) {

        final boolean updated = bookService.update(id, bookForm);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {

        final boolean deleted = bookService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
