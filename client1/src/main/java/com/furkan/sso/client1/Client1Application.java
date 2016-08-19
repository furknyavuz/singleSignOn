package com.furkan.sso.client1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * One of the single page client applications
 * Responsible of serving a single web page to user and redirects if not validated
 * For validation purpose uses rest service of auth-server
 */
@SpringBootApplication
@EnableEurekaClient
@EnableAutoConfiguration
@EnableDiscoveryClient
public class Client1Application {

    public static void main(String[] args) {
        SpringApplication.run(Client1Application.class, args);
    }
}
