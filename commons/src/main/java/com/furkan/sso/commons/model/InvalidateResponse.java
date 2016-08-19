package com.furkan.sso.commons.model;

/**
 * Invalidate reponse
 * Used for passing invalidation responses over rest service
 */
public class InvalidateResponse {

    private boolean success;
    private String message;

    public InvalidateResponse() {
        success = false;
        message = "Not invalidated";
    }

    public InvalidateResponse(boolean success, String message) {
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

    @Override
    public String toString() {
        return "InvalidateResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
