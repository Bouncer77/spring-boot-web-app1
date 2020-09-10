package com.bouncer77.springbootapp1.service;

import com.bouncer77.springbootapp1.entity.Author;

import java.util.List;
import java.util.Optional;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 10.09.2020
 */

public interface AuthorService {

    /**
     * Создает нового автора
     * @param author - автор для создания
     */
    void create(Author author);

    /**
     * Возвращает список всех имеющихся авторов
     * @return список авторов
     */
    List<Author> readAll();

    /**
     * Возвращает автора по его ID
     * @param id - ID автора
     * @return - объект автора с заданным ID
     */
    Author read(Long id);

    /**
     * Обновляет автора с заданным ID,
     * в соответствии с переданным автором
     * @param author - автор, в соответсвии с которым нужно обновить данные
     * @return - true если данные были обновлены, иначе false
     */
    boolean update(Author author);

    /**
     * Удаляет автора с заданным ID
     * @param id - id автора, которого нужно удалить
     * @return - true если автор был удален, иначе false
     */
    boolean delete(Long id);
}
