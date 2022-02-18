package com.example.testmodule;

import com.example.testmodulesecond.entities.Users;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestModuleApplication {

    public static void main(String[] args) {
        Users users = new Users();

        System.out.println(users);
        SpringApplication.run(TestModuleApplication.class, args);
    }


}
