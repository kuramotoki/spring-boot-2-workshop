package com.example.hello.greeting;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GreetingControllerLogTest {

    @Autowired
    GreetingController target;

    private MemoryAppender memoryAppender;

    private String loggerName = GreetingController.class.getName();

    @BeforeEach
    public void setup() {
        Logger logger = (Logger) LoggerFactory.getLogger(loggerName);
        memoryAppender = new MemoryAppender();
        memoryAppender.setContext((LoggerContext) LoggerFactory.getILoggerFactory());
        logger.setLevel(Level.DEBUG);
        logger.addAppender(memoryAppender);
        memoryAppender.start();
    }

    @Test
    void 正常系() {
        var result = target.greeting(new Person("kuramotoki", 18));
        Assertions.assertThat(result).isEqualTo(new Greeting("kuramotoki", "welcome!!!"));

        Assertions.assertThat(memoryAppender.countEventsForLogger(loggerName)).isEqualTo(1);
        var log1 = memoryAppender.getLoggedEvents().get(0);
        Assertions.assertThat(log1.getMessage()).isEqualTo("receive={}");
        Assertions.assertThat(log1.getArgumentArray()).containsExactly(new Person("kuramotoki", 18));
    }

}