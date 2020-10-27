package com.example.hello.greeting;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootTest
class GreetingControllerConfigTest {

    @Autowired
    GreetingController target;

    @TestConfiguration
    public static class TestConfig {
        @Bean
        @Primary
        public GreetingRepository greetingRepository() {
            return new GreetingRepository() {
                @Override
                public String findOne(int age) {
                    return "コンニチハ";
                }
            };
        }
    }

    @Test
    void 正常系() {
        var result = target.greeting(new Person("kuramotoki", 18));
        Assertions.assertThat(result).isEqualTo(new Greeting("kuramotoki", "コンニチハ"));
    }

}