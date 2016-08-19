package com.furkan.sso.client1.controller;

import com.furkan.sso.client1.rest.AuthClient;
import com.furkan.sso.commons.model.ValidateRequest;
import com.furkan.sso.commons.model.ValidateResponse;
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
 * Responsible of managing validation requests
 * Consumes rest service client of auth-server
 */
@Controller("validateController")
public class ValidateController {

    @Autowired
    @Qualifier("authClient")
    private AuthClient authClient;

    private final static Logger logger = Logger.getLogger(ValidateController.class);

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
}
