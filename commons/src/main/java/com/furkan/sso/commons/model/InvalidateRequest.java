package com.furkan.sso.commons.model;

/**
 * Invalidation request
 * Contains token
 * Used for passing invalidation requests over rest service
 */
public class InvalidateRequest {

    private String token;

    public InvalidateRequest() {
        token = "";
    }

    public InvalidateRequest(String token) {
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
        return "InvalidateRequest{" +
                "token='" + token + '\'' +
                '}';
    }
}
