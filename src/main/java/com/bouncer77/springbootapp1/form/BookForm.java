package com.bouncer77.springbootapp1.form;

import com.bouncer77.springbootapp1.entity.Author;
import com.bouncer77.springbootapp1.entity.Tag;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 11.09.2020
 */

@Getter
@Setter
public class BookForm {

    private String name;
    private int lastPage;
    private String description;
    private Set<Author> authors;
    private Set<Tag> tags;

    public BookForm() {}

    public BookForm(String name, int lastPage, String description) {
        this.name = name;
        this.lastPage = lastPage;
        this.description = description;
    }
}
