package com.bouncer77.springbootapp1.service;

import java.util.List;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 10.09.2020
 *
 * T - Entity
 * S - Entity Form
 */

public interface EntityCrudService<T, S> {

    default void createAll(List<T> eList) {
        for (T e : eList) {
            create(e);
        }
    }

    /**
     * Создает новый экземпляр сущности типа T
     *
     * @param entity - сущность для создания
     */
    void create(T entity);

    /**
     * Возвращает список всех имеющихся экземпляров сущности типа T
     *
     * @return список экземпляров сущностей
     */
    List<T> readAll();

    /**
     * Возвращает экземпляр сущности по её ID
     *
     * @param id - ID экземпляра сущности
     * @return - объект сущности с заданным ID
     */
    T read(long id);

    /**
     * Обновляет экземпляр сущности с заданным ID,
     * в соответствии с переданным экземпляром сущности
     *
     * @param entityForm - заполненная форма для изменения существующего экземпляра сущности
     * @return - true если данные были обновлены, иначе false
     */
    boolean update(long id, S entityForm);

    /**
     * Удаляет автора с заданным ID
     *
     * @param id - id автора, которого нужно удалить
     * @return - true если автор был удален, иначе false
     */
    boolean delete(long id);
}
