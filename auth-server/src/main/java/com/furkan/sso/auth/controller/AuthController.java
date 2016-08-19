package com.furkan.sso.auth.controller;

import com.furkan.sso.auth.dao.ActiveUserDao;
import com.furkan.sso.auth.entity.ActiveUser;
import com.furkan.sso.auth.ldap.LdapClient;
import com.furkan.sso.auth.util.Util;
import com.furkan.sso.commons.model.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest service controller for handling authentication requests coming to auth-server
 *
 * Has 3 Responsibilites:
 * 1. Authenticate with username and password
 * 2. Validate with authentication token
 * 3. Invalidate user with given authentication token
 *
 * To authenticate it uses LDAP
 * To validate and invalidate it uses MySQL Database
 */
@RestController
public class AuthController {

    @Autowired
    private ActiveUserDao activeUserDao;

    private final static Logger logger = Logger.getLogger(AuthController.class);

    private Util util = new Util();
    private LdapClient ldapClient = new LdapClient();

    /**
     * @param authRequest : Authentication request that contains username and password
     * @return authResponse : If authenticated returns true at success parameter and auth-token
     */
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public
    @ResponseBody
    AuthResponse auth(@RequestBody AuthRequest authRequest) {
        AuthResponse authResponse = new AuthResponse();
        logger.log(Level.INFO, "Authentication requested");

        try {
            boolean authenticated = ldapClient.authenticateWithUsernameAndPassword(authRequest.getUsername(), authRequest.getPassword());
            authResponse.setSuccess(authenticated);

            if (authenticated) {
                String token = searchForOldSession(authRequest);

                authResponse.setToken(token);
                authResponse.setMessage("Authorized");
                authResponse.setUsername(authRequest.getUsername());
            }

        } catch (Exception ex) {
            logger.log(Level.ERROR, "Exception from request: " + authRequest);
            logger.log(Level.ERROR, ex.getMessage());
        }

        logger.log(Level.INFO, "Authentication response:\n" + authResponse.toString());
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
        logger.log(Level.INFO, "Validation requested:\n" + validateRequest.toString());

        try {
            List<ActiveUser> activeUsers = activeUserDao.findByToken(validateRequest.getToken());

            if (activeUsers.size() > 0) {
                validateResponse.setSuccess(true);
                validateResponse.setMessage("Authorized");
                for (ActiveUser activeUser : activeUsers) {
                    validateResponse.setUsername(activeUser.getCredentials());
                    break;
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "Exception from request: " + validateRequest);
            logger.log(Level.ERROR, ex.getMessage());
        }

        logger.log(Level.INFO, "Validation response:\n" + validateResponse.toString());
        return validateResponse;
    }

    /**
     * @param invalidateRequest : Invalidation request that contains auth-token to remove from db
     * @return invalidateResponse : If auth-token removed returns true at success parameter username
     */
    @RequestMapping(value = "/invalidate", method = RequestMethod.POST)
    public
    @ResponseBody
    InvalidateResponse invalidate(@RequestBody InvalidateRequest invalidateRequest) {
        InvalidateResponse invalidateResponse = new InvalidateResponse();
        logger.log(Level.INFO, "Invalidation requested:\n" + invalidateRequest.toString());

        try {
            List<ActiveUser> activeUsers = activeUserDao.findByToken(invalidateRequest.getToken());

            if (activeUsers.size() > 0) {
                for (ActiveUser activeUser : activeUsers) {
                    activeUserDao.delete(activeUser);
                }
                invalidateResponse.setSuccess(true);
                invalidateResponse.setMessage("Invalidated");
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "Exception from request: " + invalidateRequest);
            logger.log(Level.ERROR, ex.getMessage());
        }

        logger.log(Level.INFO, "Invalidation response:\n" + invalidateResponse.toString());
        return invalidateResponse;
    }

    /**
     * @param authRequest : Authentication request to check for old session already exists in db or not
     * @return authToken : If old session found returns the old session's auth-token else generates new
     */
    private String searchForOldSession(@RequestBody AuthRequest authRequest) {
        String token = "";
        List<ActiveUser> activeUsers = activeUserDao.findByCredentials(authRequest.getUsername());
        if (activeUsers != null && activeUsers.size() > 0) {
            for (ActiveUser activeUser : activeUsers) {
                System.out.println("Already found");
                logger.log(Level.INFO, activeUser.toString());
                token = activeUser.getToken();
                break;
            }
        } else {
            token = util.generateAuthToken();
            ActiveUser activeUser = new ActiveUser();
            activeUser.setToken(token);
            activeUser.setCredentials(authRequest.getUsername());
            activeUserDao.save(activeUser);
        }
        return token;
    }
}
