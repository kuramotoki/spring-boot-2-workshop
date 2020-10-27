package com.example.hello.greeting;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;

@SpringBootTest
class GreetingControllerMockTest {

    @Autowired
    GreetingController target;

    @MockBean
    GreetingRepository mockRepository;

    @Test
    void 正常系() {

        when(mockRepository.findOne(anyInt())).thenReturn("元気？");

        var result = target.greeting(new Person("kuramotoki", 18));
        Assertions.assertThat(result).isEqualTo(new Greeting("kuramotoki", "元気？"));
    }

}