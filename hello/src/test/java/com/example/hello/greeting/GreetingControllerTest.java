package com.example.hello.greeting;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GreetingControllerTest {

    @Autowired
    GreetingController target;

    @Test
    void 正常系() {
        var result = target.greeting(new Person("kuramotoki", 18));
        Assertions.assertThat(result).isEqualTo(new Greeting("kuramotoki", "welcome!!!"));
    }

}