package com.bouncer77.springbootapp1.controller;

import com.bouncer77.springbootapp1.entity.Book;
import com.bouncer77.springbootapp1.entity.Person;
import com.bouncer77.springbootapp1.form.BookForm;
import com.bouncer77.springbootapp1.form.BooksCreationDto;
import com.bouncer77.springbootapp1.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 11.09.2020
 */

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class WebBookController {

    private final BookService bookService;

    // Create
    @GetMapping("/add")
    public String addBookGet(Model model) {

        BookForm bookForm = new BookForm();
        model.addAttribute("bookForm", bookForm);
        return "/book/addBook";
    }

    @PostMapping("/add")
    public String addBookPut(@ModelAttribute BookForm bookForm, Model model) {

        bookService.create(bookForm);
        return "redirect:" + "/books";
    }

    /*@GetMapping("/add")
    public String addThreeBooksGet(Model model) {

        BooksCreationDto booksCreationDto = new BooksCreationDto();

        for (int i = 0; i < 3; ++i) {
            booksCreationDto.addBook(new Book());
        }

        model.addAttribute("form", booksCreationDto);
        return "addBook";
    }

    @PostMapping("/add")
    public String addThreeBooksPut(@ModelAttribute BooksCreationDto form, Model model) {

        bookService.saveAll(form.getBooks());
        model.addAttribute("books", bookService.readAll());
        return "bookList";
    }*/

    // Read
    @GetMapping(value = "/{id}")
    public String showBook(@PathVariable(name = "id") long id,
                           Model model) {

        Book book = bookService.read(id);
        if (Objects.nonNull(book)) {
            model.addAttribute("book", book);
        } else {
            model.addAttribute("errorMsg", "Person did not found");
        }
        return "/book/showAllBooks";
    }

    @GetMapping
    public String showAllBooks(Model model) {

        model.addAttribute("books", bookService.readAll());
        return "/book/showAllBooks";
    }

    // Update
    // Delete
}
