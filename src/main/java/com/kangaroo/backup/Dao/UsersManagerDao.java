package com.kangaroo.backup.Dao;

import com.kangaroo.backup.Domain.User;
import org.springframework.stereotype.Repository;

import java.util.Vector;

/**
 * This is the Dao class for Users add and retrieve.
 */
@Repository
public class UsersManagerDao {

    private static Vector<User> users = new Vector<>();
    /**
     * Add a new user
    @param phone (NOT-REPEATABLE) is a the combination of 13 digits number.
    @param name (NOT-REPEATABLE) is string type, consisting of letters, Chinese, numbers or signals(can't be numbers totally), with length of 6~14.
    @param password is string type, consisting of letters, number or signals, with length of 8~16.
    @param sex the sex of user. 0 represents boy and 1 represents girl.
     **/
    public void addUser(String phone, String name, String password, int sex) {
        return;
    }

    /**
     * Get count of users by name and password.
     * @param name key to search
     * @param password key to search
     * @return result of retrieve(must be 0 or 1).
     */
    public int getUserCountByNameAndPassword(String name, String password) {
        return 0;
    }

    /**
     * Get count of users by phone and password.
     * @param phone key to search
     * @param password key to search
     * @return result of retrieve(must be 0 or 1).
     */
    public int getUserCountByPhoneAndPassword(String phone, String password) {
        return 0;
    }

    /**
     * Get User by name.
     * @param name the key to find.
     * @return the user find (null if not find).
     */
    public User findUserByName(String name) {
        return null;
    }

    /**
     * Get User by phone.
     * @param phone the key to find.
     * @return the user find (null if not find).
     */
    public User findUserByPhone(String phone) {
        return null;
    }

    /**
     * Get count of users by name.
     * @param name key to search
     * @return result of retrieve(must be 0 or 1).
     */
    public int getUserCountByName(String name) {
        return 0;
    }

    /**
     * Get count of users by phone.
     * @param phone key to search
     * @return result of retrieve(must be 0 or 1).
     */
    public int getUserCountByPhone(String phone) {
        return 0;
    }

}
