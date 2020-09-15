package com.bouncer77.springbootapp1.service;

import com.bouncer77.springbootapp1.entity.Book;

import com.bouncer77.springbootapp1.form.BookForm;
import com.bouncer77.springbootapp1.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 11.09.2020
 */

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public void create(Book book) {


        bookRepository.save(book);
    }

    @Override
    public List<Book> readAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book read(long id) {
        Optional<Book> bookRepOptional = bookRepository.findById(id);
        return bookRepOptional.orElse(null);
    }

    @Override
    public boolean update(long id, BookForm bookForm) {

        Optional<Book> bookRepOptional = bookRepository.findById(id);
        if (bookRepOptional.isPresent()) {
            Book book = bookRepOptional.get();
            book.setName(bookForm.getName());
            book.setDescription(bookForm.getDescription());
            book.setLastPage(bookForm.getLastPage());
            bookRepository.save(book);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(long id) {

        Optional<Book> bookRepOptional = bookRepository.findById(id);
        if (bookRepOptional.isPresent()) {
            bookRepository.delete(bookRepOptional.get());
            return true;
        }

        return false;
    }

    @Override
    public void create(BookForm bookForm) {

        Book book = new Book(bookForm.getName(), bookForm.getDescription(), bookForm.getLastPage());
        this.create(book);
    }
}
