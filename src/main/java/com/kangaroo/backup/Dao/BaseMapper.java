package com.kangaroo.backup.Dao;

import com.kangaroo.backup.Domain.BaseDomain;

import java.util.List;

public interface BaseMapper {

    /**
     * Return all entities in the database.
     * @return list including all entities
     */
    List<T> loadAll();

    /**
     * Add a new entity into database.
     * @param entity
     *        the entity to be added
     **/
    void save(T entity);

    /**
     * Update information of a entity.
     * @param id
     *        the entity id of user to update
     * @param entity
     *        the entity after updating
     */
    void update(int id, T entity);

    /**
     * Get entity matching id.
     * @param id
     *        the key to find
     * @return the entity to find (null if doesn't exist)
     */
    T loadById(int id);

    /**
     * Remove a entity in the database.
     * @param entity
     *        the entity to be removed
     */
    void remove(T entity);

}
