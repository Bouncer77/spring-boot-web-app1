package com.bouncer77.springbootapp1.service;

import com.bouncer77.springbootapp1.entity.Author;
import com.bouncer77.springbootapp1.form.AuthorForm;

import java.util.List;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 10.09.2020
 *
 * E - Entity
 * F - Entity Form
 */

public interface EntityCrudService<E, F> {

    /**
     * Создает новый экземпляр сущности типа T
     *
     * @param entity - сущность для создания
     */
    void create(E entity);

    /**
     * Возвращает список всех имеющихся экземпляров сущности типа T
     *
     * @return список экземпляров сущностей
     */
    List<E> readAll();

    /**
     * Возвращает экземпляр сущности по её ID
     *
     * @param id - ID экземпляра сущности
     * @return - объект сущности с заданным ID
     */
    E read(long id);

    /**
     * Обновляет экземпляр сущности с заданным ID,
     * в соответствии с переданным экземпляром сущности
     *
     * @param entityForm - заполненная форма для изменения существующего экземпляра сущности
     * @return - true если данные были обновлены, иначе false
     */
    boolean update(long id, F entityForm);

    /**
     * Удаляет автора с заданным ID
     *
     * @param id - id автора, которого нужно удалить
     * @return - true если автор был удален, иначе false
     */
    boolean delete(long id);
}
