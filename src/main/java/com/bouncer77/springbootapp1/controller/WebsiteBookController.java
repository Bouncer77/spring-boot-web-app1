package com.bouncer77.springbootapp1.controller;

import com.bouncer77.springbootapp1.entity.Book;
import com.bouncer77.springbootapp1.form.ListBookForm;
import com.bouncer77.springbootapp1.repository.BookRepository;
import com.bouncer77.springbootapp1.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 11.09.2020
 */

@Controller
@RequestMapping("/web/books")
public class WebsiteBookController {

    @Autowired
    private BookService bookService;

    @GetMapping //(value = "/all")
    public String showBooks(Model model) {
        model.addAttribute("books", bookService.readAll());
        return "book/allBooks";
    }

    @GetMapping("/add")
    public String addBooks(Model model) {

        ListBookForm listBookForm = new ListBookForm();

        listBookForm.addBook(new Book("Книга 1", "Описание 1", 100));
        listBookForm.addBook(new Book("Книга 2", "Описание 2", 200));
        listBookForm.addBook(new Book("Книга 3", "Описание 3", 300));

        model.addAttribute("books", listBookForm.getBooks());
        return "book/addBook";
    }

    /*@PostMapping("/save")
    public String saveBooks(@ModelAttribute ListBookForm ListBookForm, Model model) {

        bookRepository.saveAll(ListBookForm.getBooks());
        model.addAttribute("books", bookRepository.findAll());
        return "redirect:web/books";
    }

    @GetMapping(value = "/edit")
    public String showEditForm(Model model) {
        List<Book> books = new ArrayList<>();
        bookService.findAll()
                .iterator()
                .forEachRemaining(books::add);

        model.addAttribute("form", new BooksCreationDto(books));

        return "editBooksForm";
    }*/
}
