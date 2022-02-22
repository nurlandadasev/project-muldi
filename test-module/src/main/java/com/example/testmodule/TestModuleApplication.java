package com.example.testmodule;

import com.example.testmodulesecond.entities.Users;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestModuleApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TestModuleApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Users users = new Users();

        System.out.println(users);
        System.out.println("AND NEW PUSH!!");
    }
}
