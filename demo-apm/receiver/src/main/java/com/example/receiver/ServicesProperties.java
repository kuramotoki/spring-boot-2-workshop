package com.example.receiver;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "services")
@Data
public class ServicesProperties {

    private String processorUri;
    private String publisherUri;

}
