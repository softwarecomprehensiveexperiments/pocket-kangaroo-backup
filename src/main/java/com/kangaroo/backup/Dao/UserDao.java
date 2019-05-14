package com.kangaroo.backup.Dao;

import com.kangaroo.backup.Domain.User;
import org.springframework.stereotype.Repository;

import java.util.Vector;

/**
 * This is the Dao class for Users add and retrieve.
 */
@Repository
public class UserDao extends BaseDao<User> {

    private static Vector<User> users = new Vector<>();

    /**
     * Check if a user matching name and password.
     * @param name
     *        name to retrieve
     * @param password
     *        password to retrieve
     * @return true if a user matches, false else.
     */
    public boolean hasMatchNameAndPassword(String name, String password) {
        //todo
    }

    /**
     * Check if a user matching phone and password.
     * @param phone
     *        phone to retrieve
     * @param password
     *        password to retrieve
     * @return true if a user matches, false else.
     */
    public boolean hasMatchPhoneAndPassword(String phone, String password) {
        //todo
    }
    /**
     * Get User matching name.
     * @param name
     *        the key to find.
     * @return the user find (null if doesn't exist).
     */
    public User loadByName(String name) {
        //todo
    }

    /**
     * Get User matching phone.
     * @param phone
     *        the key to find.
     * @return the user find (null if doesn't exist).
     */
    public User loadByPhone(String phone) {
        //todo
    }


}
