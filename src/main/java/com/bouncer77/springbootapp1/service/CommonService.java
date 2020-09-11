package com.bouncer77.springbootapp1.service;

import com.bouncer77.springbootapp1.entity.AbstractEntity;

import java.util.List;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 11.09.2020
 */

public interface CommonService <E extends AbstractEntity> {

    /**
     * Создает новый экземпляр сущности типа T
     *
     * @param entity - сущность для создания
     */
    void save(E entity);

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
     * @param entity - экземпляр сущности
     * @return - true если данные были обновлены, иначе false
     */
    boolean update(E entity);

    /**
     * Удаляет автора с заданным ID
     *
     * @param id - id автора, которого нужно удалить
     * @return - true если автор был удален, иначе false
     */
    boolean delete(long id);
}
