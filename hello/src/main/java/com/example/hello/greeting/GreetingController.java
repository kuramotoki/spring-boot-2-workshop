package com.example.hello.greeting;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class GreetingController {

    @Autowired
    GreetingRepository repository;

    @PostMapping("greeting")
    @ResponseBody
    public Greeting greeting(@RequestBody Person person) {
        log.info("receive={}", person);

        var msg = repository.findOne(person.getAge());

        return new Greeting(person.getName(), msg);
    }
}
