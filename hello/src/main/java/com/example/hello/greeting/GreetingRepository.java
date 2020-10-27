package com.example.hello.greeting;

import org.springframework.stereotype.Repository;

@Repository
public class GreetingRepository {

    public String findOne(int age) {
        if (age < 18) {
            return "yo!!!";
        }
        return "welcome!!!";
    }
}
