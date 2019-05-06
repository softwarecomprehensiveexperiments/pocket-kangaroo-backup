package com.kangaroo.backup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class KangarooBackupApplication {

    public static void main(String[] args) {
        SpringApplication.run(KangarooBackupApplication.class, args);
    }

    @RequestMapping(value = "/in", method = RequestMethod.GET)
    public String hello() {
        return "HELLO, Kangaroo";
    }
}


