package com.example.hello.echo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class EchoController {

    @GetMapping("echo")
    public String echo(@RequestParam(value = "msg", defaultValue = "come-on!!") String msg) {
        log.info("received msg={}", msg);
        return msg;
    }
}
