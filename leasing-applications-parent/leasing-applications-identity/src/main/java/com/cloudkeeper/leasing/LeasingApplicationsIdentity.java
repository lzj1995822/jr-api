package com.cloudkeeper.leasing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@EnableJpaAuditing
@EnableEurekaClient
public class LeasingApplicationsIdentity {

    public static void main(String[] args) {
        SpringApplication.run(LeasingApplicationsIdentity.class, args);
    }
    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("1024000KB");
        return factory.createMultipartConfig();
    }
}
