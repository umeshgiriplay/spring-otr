package com.umeshgiri.otr;

import com.umeshgiri.otr.auth.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableConfigurationProperties(AppProperties.class)
public class OTRApplication {

    public static void main(String[] args) {
        SpringApplication.run(OTRApplication.class, args);
    }
}
