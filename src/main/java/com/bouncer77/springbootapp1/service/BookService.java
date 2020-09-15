package com.bouncer77.springbootapp1.service;

import com.bouncer77.springbootapp1.entity.Book;
import com.bouncer77.springbootapp1.form.BookForm;
import com.bouncer77.springbootapp1.form.PersonForm;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 11.09.2020
 */

public interface BookService extends EntityCrudService<Book, BookForm> {

    void create(BookForm bookForm);
}
