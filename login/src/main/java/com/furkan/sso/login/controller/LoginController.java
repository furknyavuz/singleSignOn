package com.furkan.sso.login.controller;

import com.furkan.sso.commons.model.*;
import com.furkan.sso.login.rest.AuthClient;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Responsible of managing authentication and validation requests
 * Consumes rest service client of auth-server
 */
@Controller("loginController")
public class LoginController {

    @Autowired
    @Qualifier("authClient")
    private AuthClient authClient;

    private final static Logger logger = Logger.getLogger(LoginController.class);

    /**
     * @param authRequest : Authentication request that contains username and password
     * @return authResponse : If authenticated returns true at success parameter and auth-token
     */
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public
    @ResponseBody
    AuthResponse auth(@RequestBody AuthRequest authRequest) {
        AuthResponse authResponse = new AuthResponse();
        try {
            authResponse = authClient.auth(authRequest.getUsername(), authRequest.getPassword());
        } catch (Exception ex) {
            logger.log(Level.ERROR, "Exception from request: " + authRequest);
            logger.log(Level.ERROR, ex.getMessage());
        }
        return authResponse;
    }

    /**
     * @param validateRequest : Validation request that contains auth-token to check
     * @return validateResponse : If validated returns true at success parameter and validated username
     */
    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public
    @ResponseBody
    ValidateResponse validate(@RequestBody ValidateRequest validateRequest) {
        ValidateResponse validateResponse = new ValidateResponse();
        try {
            validateResponse = authClient.validate(validateRequest.getToken());
        } catch (Exception ex) {
            logger.log(Level.ERROR, "Exception from request: " + validateRequest);
            logger.log(Level.ERROR, ex.getMessage());
        }
        return validateResponse;
    }

    /**
     * @param invalidateRequest : Invalidation request that contains auth-token to invalidate
     * @return invalidateResponse : If invalidated returns true at success parameter
     */
    @RequestMapping(value = "/invalidate", method = RequestMethod.POST)
    public
    @ResponseBody
    InvalidateResponse invalidate(@RequestBody InvalidateRequest invalidateRequest) {
        InvalidateResponse invalidateResponse = new InvalidateResponse();
        try {
            invalidateResponse = authClient.invalidate(invalidateRequest.getToken());
        } catch (Exception ex) {
            logger.log(Level.ERROR, "Exception from request: " + invalidateRequest);
            logger.log(Level.ERROR, ex.getMessage());
        }
        return invalidateResponse;
    }
}
