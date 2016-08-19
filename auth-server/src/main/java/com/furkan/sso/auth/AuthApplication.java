package com.furkan.sso.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Authentication server application
 * Responsible of serving authentication and validation rest services to clients
 * Connects to Ldap and MySql
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@EnableAutoConfiguration
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
