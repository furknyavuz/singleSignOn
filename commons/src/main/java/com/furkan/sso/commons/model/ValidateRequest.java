package com.furkan.sso.commons.model;

/**
 * Validation request
 * Contains token
 * Used for passing validation requests over rest service
 */
public class ValidateRequest {

    private String token;

    public ValidateRequest() {
        token = "";
    }

    public ValidateRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "ValidateRequest{" +
                "token='" + token + '\'' +
                '}';
    }
}
