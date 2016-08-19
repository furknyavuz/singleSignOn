package com.furkan.sso.commons.model;

/**
 * Validate reponse
 * Used for passing validation responses over rest service
 */
public class ValidateResponse {

    private boolean success;
    private String message;
    private String username;

    public ValidateResponse() {
        success = false;
        message = "Unauthorized";
        username = "";
    }

    public ValidateResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "ValidateResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
