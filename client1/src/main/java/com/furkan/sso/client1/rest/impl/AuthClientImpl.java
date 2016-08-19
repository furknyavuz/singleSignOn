package com.furkan.sso.client1.rest.impl;

import com.furkan.sso.client1.rest.AuthClient;
import com.furkan.sso.commons.model.ValidateRequest;
import com.furkan.sso.commons.model.ValidateResponse;
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

    /**
     * @param token : auth-token to validate
     * @return If validated returns true at success parameter and validated username
     */
    @Override
    public ValidateResponse validate(String token) {
        String serviceUrl = "http://auth-server/validate";
        logger.log(Level.INFO, "Validation requested with token: " + token + " to: " + serviceUrl);

        ValidateRequest validateRequest = new ValidateRequest(token);
        HttpEntity<ValidateRequest> requestEntity = new HttpEntity<>(validateRequest);

        ResponseEntity<ValidateResponse> response = restTemplate.exchange(serviceUrl, HttpMethod.POST, requestEntity, ValidateResponse.class, new HashMap<>());

        logger.log(Level.INFO, "Validation response:\n" + response.getBody());
        return response.getBody();
    }
}
