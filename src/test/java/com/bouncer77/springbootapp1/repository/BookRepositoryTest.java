package com.bouncer77.springbootapp1.repository;

import com.bouncer77.springbootapp1.entity.Author;
import com.bouncer77.springbootapp1.entity.Book;
import com.bouncer77.springbootapp1.entity.Tag;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Assertions;
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

    @Autowired
    TagRepository tagRepository;

    @Test
    @Transactional
    public void bookOneAutorTest() {

        Book book = bookRepository.findByName("Война и мир");
        Author author = authorRepository.findBySurname("Достоевский");
        assertThat(book.getAuthors().contains(author)).isTrue();
    }

    @Test
    public void bookAutorsLazyLoadingException() {
        Book book = bookRepository.findByName("Изучаем Java");
        //session closed
        Assertions.assertThrows(LazyInitializationException.class, () -> System.out.println(book.getAuthors()));
    }

    @Test
    @Transactional
    public void bookFewAutorsTest() {

        Book book = bookRepository.findByName("Изучаем Java");
        assertThat(book.getAuthors()).hasSize(2);

        Author author1 = authorRepository.findBySurname("Сьерра");
        Author author2 = authorRepository.findBySurname("Бэйтс");
        assertThat(book.getAuthors().contains(author1)).isTrue();
        assertThat(book.getAuthors().contains(author2)).isTrue();
    }

    @Test
    @Transactional
    public void bookFewTagsTest() {

        Book book = bookRepository.findByName("Изучаем Java");
        assertThat(book.getTags()).hasSize(2);

        Tag tag1 = tagRepository.findByName("Java");
        Tag tag2 = tagRepository.findByName("Программирование");
        assertThat(book.getTags().contains(tag1)).isTrue();
        assertThat(book.getTags().contains(tag2)).isTrue();
    }

}