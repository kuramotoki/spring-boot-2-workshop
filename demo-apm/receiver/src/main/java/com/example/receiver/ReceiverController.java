package com.example.receiver;

import brave.Span;
import brave.Tracer;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class ReceiverController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ServicesProperties servicesProperties;

    @Autowired
    Tracer tracer;

    @GetMapping("receive")
    public String receive(@RequestParam(value = "name") String name) {

        var processed = callProcessor(name);

        return callPublisher(processed);
    }

    @SneakyThrows
    String callProcessor(String name) {
        Span newSpan = tracer.nextSpan().name("callProcessor").start();
        try (Tracer.SpanInScope ws = tracer.withSpanInScope(newSpan.start())) {
            TimeUnit.MILLISECONDS.sleep(100);
            return restTemplate.getForObject(servicesProperties.getProcessorUri(), String.class, name);
        } finally {
            newSpan.finish();
        }
    }

    @SneakyThrows
    String callPublisher(String processed) {
        Span newSpan = tracer.nextSpan().name("callPublisher").start();
        try (Tracer.SpanInScope ws = tracer.withSpanInScope(newSpan.start())) {
            TimeUnit.MILLISECONDS.sleep(200);
            return restTemplate.getForObject(servicesProperties.getPublisherUri(), String.class, processed);
        } finally {
            newSpan.finish();
        }
    }

}
