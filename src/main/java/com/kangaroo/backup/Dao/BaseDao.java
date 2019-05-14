package com.kangaroo.backup.Dao;

import com.kangaroo.backup.Domain.BaseDomain;

import java.util.List;

public class BaseDao<T extends BaseDomain> {

    /**
     * Return all entities in the database.
     * @return list including all entities
     */
    public List<T> loadAll() {
        //todo
    }

    /**
     * Add a new entity into database.
     * @param entity
     *        the entity to be added
     **/
    public void save(T entity) {
        //todo
    }

    /**
     * Update information of a entity.
     * @param id
     *        the entity id of user to update
     * @param entity
     *        the entity after updating
     */
    public void update(int id, T entity) {
        //todo
    }

    /**
     * Get entity matching id.
     * @param id
     *        the key to find
     * @return the entity to find (null if doesn't exist)
     */
    public T loadById(int id) {
        //todo
    }

    /**
     * Remove a entity in the database.
     * @param entity
     *        the entity to be removed
     */
    public void remove(T entity) {
        //todo
    }
}
