package com.kangaroo.backup.Dao;

import com.kangaroo.backup.Domain.User;

import java.util.List;

public interface UserMapper {

    /**
     * Get User matching name.
     *
     * @return the user list (null if doesn't exist).
     */
    List<User> loadAll();

    /**
     * Get User matching name.
     * @param name
     *        the key to find.
     * @return the user find (null if doesn't exist).
     */
    User loadByName(String name);

    /**
     * Get User matching phone.
     * @param phone
     *        the key to find.
     * @return the user find (null if doesn't exist).
     */
    User loadByPhone(String phone);

    /**
    * @param user
    *       a user
    * @function add a new user to the database
    *
    */
    void insert(User user);

    /**
    * @param user
    *       the user to update
    * @function find the user in DB and update it
    */
    void update(User user);

    /**
     * @param id
     *       the id of the user to be delete
     * @function find the user by id in DB and delete it
     */
    void delete(int id);
}
