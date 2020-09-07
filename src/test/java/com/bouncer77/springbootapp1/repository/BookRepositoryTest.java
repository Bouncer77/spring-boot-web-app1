package com.bouncer77.springbootapp1.repository;

import com.bouncer77.springbootapp1.entity.Author;
import com.bouncer77.springbootapp1.entity.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Test
    @Transactional
    public void autorBookOneAutorTest() {

        Book book = bookRepository.findByName("Война и мир");
        Author author = authorRepository.findBySurname("Достоевский");
        assertThat(book.getAuthors().contains(author)).isTrue();
    }

    @Test
    @Transactional
    public void autorBookFewAutorTest() {

        Book book = bookRepository.findByName("Изучаем Java");
        assertThat(book.getAuthors()).hasSize(2);

        Author author1 = authorRepository.findBySurname("Сьерра");
        Author author2 = authorRepository.findBySurname("Бэйтс");
        assertThat(book.getAuthors().contains(author1)).isTrue();
        assertThat(book.getAuthors().contains(author2)).isTrue();
    }

}