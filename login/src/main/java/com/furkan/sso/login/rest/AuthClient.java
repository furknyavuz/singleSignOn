package com.furkan.sso.login.rest;

import com.furkan.sso.commons.model.AuthResponse;
import com.furkan.sso.commons.model.InvalidateResponse;
import com.furkan.sso.commons.model.ValidateResponse;

/**
 * Authentication client interface
 * AuthClientImpl is rest implementation of this class
 */
public interface AuthClient {

    /**
     *
     * @param username : username
     * @param password : password
     * @return If authenticated returns true at success parameter and auth-token
     */
    AuthResponse auth(String username, String password);

    /**
     * @param token : auth-token to validate
     * @return If validated returns true at success parameter and validated username
     */
    ValidateResponse validate(String token);

    /**
     * @param token : auth-token to invalidate
     * @return If invalidated returns true at success parameter
     */
    InvalidateResponse invalidate(String token);
}