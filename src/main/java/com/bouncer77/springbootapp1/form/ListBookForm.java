package com.bouncer77.springbootapp1.form;

import com.bouncer77.springbootapp1.entity.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 11.09.2020
 */

@Getter
@Setter
public class ListBookForm {

    private List<Book> books = new ArrayList<>();

    public ListBookForm() {}

    public ListBookForm(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }
}
