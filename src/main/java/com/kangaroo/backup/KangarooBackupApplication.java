package com.kangaroo.backup;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ServletComponentScan
@RestController
@MapperScan("com.kangaroo.backup.Dao")
public class KangarooBackupApplication {

    public static void main(String[] args) {
        SpringApplication.run(KangarooBackupApplication.class, args);
    }

    @RequestMapping(value = "/in", method = RequestMethod.GET)
    public String test() {
        return "HELLO. Kangaroo test.";
    }
}


