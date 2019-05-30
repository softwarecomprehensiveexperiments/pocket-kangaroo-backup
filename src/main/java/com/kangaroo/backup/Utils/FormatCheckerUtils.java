package com.kangaroo.backup.Utils;

public class FormatCheckerUtils {

    private static final String CHECK_TRANS_NAME_REGEX = "^[a-zA-Z][a-zA-Z0-9_]{5,13}$";

    private static final String MATCH_CHINESE_REGEX = "[\\u4e00-\\u9fa5]";

    private static final String CHECK_PASSWORD_REGEX = "(?=[a-zA-Z0-9!@#$%^&*]{8,16})^.*(?=([0-9](?=[a-zA-Z!@#$%^&*]))|([!@#$%^&*](?=[a-zA-Z0-9]))|([a-zA-Z](?=[0-9!@#$%^&*]))).*$";

    private static final String CHECK_PHONE_REGEX = "^1(?:(3[0-9])|(4[5-7])|(5[0-9])|(7[0-9])|(8[0-9]))+\\d{8}$";

    public static boolean isValidPhone(String phone) {
        if(phone == null || phone.isEmpty()) {
            return false;
        }
        return phone.matches(CHECK_PHONE_REGEX);
    }

    public static boolean isValidName(String name) {
        if(name == null || name.isEmpty()) {
            return false;
        }
        name.replaceAll(MATCH_CHINESE_REGEX, "AA");
        return name.matches(CHECK_TRANS_NAME_REGEX);
    }

    public static boolean isValidPassword(String password) {
        if(password == null || password.isEmpty()) {
            return false;
        }
        return password.matches(CHECK_PASSWORD_REGEX);
    }
}
