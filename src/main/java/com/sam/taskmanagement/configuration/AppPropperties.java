package com.sam.taskmanagement.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppPropperties {
    String secretKey;
}
