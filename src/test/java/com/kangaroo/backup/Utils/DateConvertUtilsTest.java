package com.kangaroo.backup.Utils;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class DateConvertUtilsTest {

    @Test
    public void stringToDate() {
        Date date = DateConvertUtils.stringToDate("1998-08-20 12:21:12");
        System.out.println(date);
        System.out.println(DateConvertUtils.dateToString(date));
    }
}