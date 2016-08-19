package com.furkan.sso.auth.util;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Utility functions
 */
public final class Util {

    private SecureRandom random = new SecureRandom();

    /**
     * Generates auth-token
     *
     * @return generated auth-token
     */
    public String generateAuthToken() {
        return new BigInteger(130, random).toString(32);
    }
}
