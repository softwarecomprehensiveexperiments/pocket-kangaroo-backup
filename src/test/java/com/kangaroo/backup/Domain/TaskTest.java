package com.kangaroo.backup.Domain;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class TaskTest {

    @Test
    public void getTaskPublishDate() {
        String test = String.format("%tD %<tT", new Date());
        System.out.println("---" + test);
    }
}