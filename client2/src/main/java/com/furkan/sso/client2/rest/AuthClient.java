package com.furkan.sso.client2.rest;

import com.furkan.sso.commons.model.ValidateResponse;

/**
 * Authentication client interface
 * AuthClientImpl is rest implementation of this class
 */
public interface AuthClient {

    /**
     * @param token : auth-token to validate
     * @return If validated returns true at success parameter and validated username
     */
    ValidateResponse validate(String token);
}