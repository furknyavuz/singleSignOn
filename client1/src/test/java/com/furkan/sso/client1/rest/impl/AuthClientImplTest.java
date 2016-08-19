package com.furkan.sso.client1.rest.impl;

import com.furkan.sso.client1.rest.AuthClient;
import com.furkan.sso.commons.model.ValidateResponse;
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
}
