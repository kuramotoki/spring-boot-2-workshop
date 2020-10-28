package com.example.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class ReceiverController {

    @Autowired
    RestTemplate restTemplate;

    private static String processorURI = "http://localhost:8081/process?text={text}";

    private static String publisherURI = "http://localhost:8082/publish?text={text}";

    @GetMapping("receive")
    public String receive(@RequestParam(value = "name") String name) {

        var processed = restTemplate.getForObject(processorURI, String.class, name);

        var result = restTemplate.getForObject(publisherURI, String.class, processed);

        return result;
    }
}
