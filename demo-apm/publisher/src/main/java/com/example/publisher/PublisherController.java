package com.example.publisher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PublisherController {

    @RequestMapping("publish")
    public String publish(@RequestParam(value = "text", defaultValue = "none") String text) {
        log.info("publish={}", text);
        return "ok";
    }
}
