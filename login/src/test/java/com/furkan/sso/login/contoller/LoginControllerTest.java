package com.furkan.sso.login.contoller;

import com.furkan.sso.commons.model.*;
import com.furkan.sso.login.controller.LoginController;
import com.furkan.sso.login.rest.AuthClient;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.reflect.Whitebox.setInternalState;

public class LoginControllerTest {

    @Test
    public void testAuth() {
        // given
        String testToken = "qqn4pc1vjul8k223c20uqrne8r";
        String testUser = "testUser";
        String testPassword = "testPassword";
        String testMessage = "Authenticated";
        AuthResponse authResponse = new AuthResponse();
        authResponse.setUsername(testUser);
        authResponse.setMessage(testMessage);
        authResponse.setToken(testToken);
        authResponse.setSuccess(true);

        LoginController loginController = new LoginController();
        AuthClient authClient = mock(AuthClient.class);
        doReturn(authResponse).when(authClient).auth(testUser, testPassword);
        setInternalState(loginController, "authClient", authClient);

        // when
        AuthRequest authRequest = new AuthRequest(testUser, testPassword);
        AuthResponse result = loginController.auth(authRequest);

        // then
        assertTrue(result.isSuccess());
        assertEquals(testMessage, result.getMessage());
        assertEquals(testToken, result.getToken());
        assertEquals(testUser, result.getUsername());
    }

    @Test
    public void testValidate() {
        // given
        String testToken = "qqn4pc1vjul8k223c20uqrne8r";
        String testUser = "testUser";
        String testMessage = "Validated";
        ValidateResponse validateResponse = new ValidateResponse();
        validateResponse.setUsername(testUser);
        validateResponse.setMessage(testMessage);
        validateResponse.setSuccess(true);

        LoginController loginController = new LoginController();
        AuthClient authClient = mock(AuthClient.class);
        doReturn(validateResponse).when(authClient).validate(testToken);
        setInternalState(loginController, "authClient", authClient);

        // when
        ValidateRequest validateRequest = new ValidateRequest(testToken);
        ValidateResponse result = loginController.validate(validateRequest);

        // then
        assertTrue(result.isSuccess());
        assertEquals(testMessage, result.getMessage());
        assertEquals(testUser, result.getUsername());
    }

    @Test
    public void testInvalidate() {
        // given
        String testToken = "qqn4pc1vjul8k223c20uqrne8r";
        String testMessage = "Invalidated";
        InvalidateResponse invalidateResponse = new InvalidateResponse();
        invalidateResponse.setMessage(testMessage);
        invalidateResponse.setSuccess(true);

        LoginController loginController = new LoginController();
        AuthClient authClient = mock(AuthClient.class);
        doReturn(invalidateResponse).when(authClient).invalidate(testToken);
        setInternalState(loginController, "authClient", authClient);

        // when
        InvalidateRequest invalidateRequest = new InvalidateRequest(testToken);
        InvalidateResponse result = loginController.invalidate(invalidateRequest);

        // then
        assertTrue(result.isSuccess());
        assertEquals(testMessage, result.getMessage());
    }
}
