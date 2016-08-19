package com.furkan.sso.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Login application is centralized login module of single sign-on system
 * Responsible of serving login page to redirected users
 * Consumes rest services of authentication module
 */
@SpringBootApplication
@EnableEurekaClient
@EnableAutoConfiguration
@EnableDiscoveryClient
public class LoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginApplication.class, args);
    }
}
