package com.example.hello.greeting;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class GreetingController {

    @PostMapping("greeting")
    @ResponseBody
    public Greeting greeting(@RequestBody Person person) {
        log.info("receive={}", person);
        return new Greeting(person.getName(), "welcome!!!");
    }
}
