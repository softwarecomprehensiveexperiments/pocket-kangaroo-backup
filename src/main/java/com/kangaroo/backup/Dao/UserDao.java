package com.kangaroo.backup.Dao;

import com.kangaroo.backup.Domain.User;

public class UserDao {

    /**
     * Add a new user
    @param phone (NOT-REPEATABLE) is a the combination of 13 digits number.
    @param name (NOT-REPEATABLE) is string type, consisting of letters, Chinese, numbers or signals, with length of 6~14.
    @param password is string type, consisting of letters, number or signals, with length of 8~16.
    @param sex the sex of user. 0 represents boy and 1 represents girl.
     **/
    public void addUser(String phone, String name, String password, int sex) {

    }

    /**
     * Get count of users by name and password.
     * @param name
     * @param password
     * @return result of retrieve
     */
    public int getUserCountByName(String name, String password) {

    }

    public User findUserByName(String name, )
}
