package com.kangaroo.backup.Dao;

import com.kangaroo.backup.Domain.User;
import org.springframework.stereotype.Repository;

import java.util.Vector;

/**
 * This is the Dao class for Users add and retrieve.
 */
@Repository
public class UserDao {

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
        for(User user : users) {
            if(user.getName().equals(name) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
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
        return false;
    }
    /**
     * Get User matching name.
     * @param name
     *        the key to find.
     * @return the user find (null if doesn't exist).
     */
    public User loadByName(String name) {
        //todo
        for(User user : users) {
            if(user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Get User matching phone.
     * @param phone
     *        the key to find.
     * @return the user find (null if doesn't exist).
     */
    public User loadByPhone(String phone) {
        //todo
        return null;
    }

    public void save(User user) {
        users.add(user);
    }

    public User loadById(int id) {
        return null;
    }
}
