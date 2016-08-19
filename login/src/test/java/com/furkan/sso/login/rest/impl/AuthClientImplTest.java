package com.furkan.sso.login.rest.impl;

import com.furkan.sso.commons.model.AuthResponse;
import com.furkan.sso.commons.model.InvalidateResponse;
import com.furkan.sso.commons.model.ValidateResponse;
import com.furkan.sso.login.rest.AuthClient;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.reflect.Whitebox.setInternalState;

public class AuthClientImplTest {

    @Test
    public void testAuth() {
        // given
        RestTemplate restTemplate = mock(RestTemplate.class);
        AuthClient authClient = new AuthClientImpl();
        ResponseEntity response = mock(ResponseEntity.class);
        String testToken = "qqn4pc1vjul8k223c20uqrne8r";
        String testUser = "testUser";
        String testPassword = "testPassword";
        String testMessage = "Validated";
        AuthResponse authResponse = new AuthResponse();
        authResponse.setUsername(testUser);
        authResponse.setMessage(testMessage);
        authResponse.setToken(testToken);
        authResponse.setSuccess(true);

        doReturn(authResponse).when(response).getBody();
        doReturn(response).when(restTemplate).exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class), any(HashMap.class));
        setInternalState(authClient, "restTemplate", restTemplate);

        // when
        AuthResponse result = authClient.auth(testUser, testPassword);

        // then
        assertTrue(result.isSuccess());
        assertEquals(testMessage, result.getMessage());
        assertEquals(testUser, result.getUsername());
        assertEquals(testToken, result.getToken());
    }

    @Test
    public void testValidate() {
        // given
        RestTemplate restTemplate = mock(RestTemplate.class);
        AuthClient authClient = new AuthClientImpl();
        ResponseEntity response = mock(ResponseEntity.class);
        String testToken = "qqn4pc1vjul8k223c20uqrne8r";
        String testUser = "testUser";
        String testMessage = "Validated";
        ValidateResponse validateResponse = new ValidateResponse();
        validateResponse.setUsername(testUser);
        validateResponse.setMessage(testMessage);
        validateResponse.setSuccess(true);

        doReturn(validateResponse).when(response).getBody();
        doReturn(response).when(restTemplate).exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class), any(HashMap.class));
        setInternalState(authClient, "restTemplate", restTemplate);

        // when
        ValidateResponse result = authClient.validate(testToken);

        // then
        assertTrue(result.isSuccess());
        assertEquals(testMessage, result.getMessage());
        assertEquals(testUser, result.getUsername());
    }

    @Test
    public void testInvalidate() {
        // given
        RestTemplate restTemplate = mock(RestTemplate.class);
        AuthClient authClient = new AuthClientImpl();
        ResponseEntity response = mock(ResponseEntity.class);
        String testToken = "qqn4pc1vjul8k223c20uqrne8r";
        String testUser = "testUser";
        String testMessage = "Invalidated";
        InvalidateResponse invalidateResponse = new InvalidateResponse();
        invalidateResponse.setMessage(testMessage);
        invalidateResponse.setSuccess(true);

        doReturn(invalidateResponse).when(response).getBody();
        doReturn(response).when(restTemplate).exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class), any(HashMap.class));
        setInternalState(authClient, "restTemplate", restTemplate);

        // when
        InvalidateResponse result = authClient.invalidate(testToken);

        // then
        assertTrue(result.isSuccess());
        assertEquals(testMessage, result.getMessage());
    }
}
