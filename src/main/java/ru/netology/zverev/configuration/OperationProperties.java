package ru.netology.zverev.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "operations.processing")
public class OperationProperties {
    private int timeout;
}