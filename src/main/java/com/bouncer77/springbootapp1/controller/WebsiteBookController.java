package com.bouncer77.springbootapp1.controller;

import com.bouncer77.springbootapp1.entity.Book;
import com.bouncer77.springbootapp1.form.BooksCreationDto;
import com.bouncer77.springbootapp1.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 11.09.2020
 */

@Controller
@RequestMapping("/web/books")
public class WebsiteBookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public String showBooks(Model model) {
        model.addAttribute("books", bookService.readAll());
        return "book/all";
    }

    @GetMapping("/add")
    public String showCreateForm(Model model) {

        BooksCreationDto booksCreationDto = new BooksCreationDto();

        for (int i = 0; i < 3; ++i) {
            booksCreationDto.addBook(new Book());
        }

        model.addAttribute("form", booksCreationDto);
        return "book/add";
    }

    @PostMapping("/add")
    public String saveBooks(@ModelAttribute BooksCreationDto form, Model model) {


        bookService.saveAll(form.getBooks());

        model.addAttribute("books", bookService.readAll());
        return "book/all";
    }
}
