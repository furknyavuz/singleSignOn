package com.furkan.sso.login.rest.impl;

import com.furkan.sso.commons.model.*;
import com.furkan.sso.login.rest.AuthClient;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Rest service implementation of AuthClient interface
 * Responsible of posting validation requests to rest service
 */
@Service("authClient")
public class AuthClientImpl implements AuthClient {

    @Autowired
    private RestTemplate restTemplate;

    private final static Logger logger = Logger.getLogger(AuthClientImpl.class);

    private final String authServer = "http://auth-server";

    /**
     * @param username : username
     * @param password : password
     * @return If authenticated returns true at success parameter and auth-token
     */
    @Override
    public AuthResponse auth(String username, String password) {
        String serviceUrl = authServer + "/auth";
        logger.log(Level.INFO, "Authentication requested with username: " + username + " to: " + serviceUrl);

        AuthRequest authRequest = new AuthRequest(username, password);
        HttpEntity<AuthRequest> requestEntity = new HttpEntity<>(authRequest);

        ResponseEntity<AuthResponse> response = restTemplate.exchange(serviceUrl, HttpMethod.POST, requestEntity, AuthResponse.class, new HashMap<>());

        logger.log(Level.INFO, "Authentication response:\n" + response.getBody());
        return response.getBody();
    }

    /**
     * @param token : auth-token to validate
     * @return If validated returns true at success parameter and validated username
     */
    @Override
    public ValidateResponse validate(String token) {
        String serviceUrl = authServer + "/validate";
        logger.log(Level.INFO, "Validation requested with token: " + token + " to: " + serviceUrl);

        ValidateRequest validateRequest = new ValidateRequest(token);
        HttpEntity<ValidateRequest> requestEntity = new HttpEntity<>(validateRequest);

        ResponseEntity<ValidateResponse> response = restTemplate.exchange(serviceUrl, HttpMethod.POST, requestEntity, ValidateResponse.class, new HashMap<>());

        logger.log(Level.INFO, "Validation response:\n" + response.getBody());
        return response.getBody();
    }

    /**
     * @param token : auth-token to invalidate
     * @return If invalidated returns true at success parameter
     */
    @Override
    public InvalidateResponse invalidate(String token) {
        String serviceUrl = authServer + "/invalidate";
        logger.log(Level.INFO, "Invalidation requested with token: " + token + " to: " + serviceUrl);

        InvalidateRequest invalidateRequest = new InvalidateRequest(token);
        HttpEntity<InvalidateRequest> requestEntity = new HttpEntity<>(invalidateRequest);

        ResponseEntity<InvalidateResponse> response = restTemplate.exchange(serviceUrl, HttpMethod.POST, requestEntity, InvalidateResponse.class, new HashMap<>());

        logger.log(Level.INFO, "Invalidation response:\n" + response.getBody());
        return response.getBody();
    }
}
