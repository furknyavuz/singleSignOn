package com.furkan.sso.client2.controller;

import com.furkan.sso.client2.rest.AuthClient;
import com.furkan.sso.commons.model.ValidateRequest;
import com.furkan.sso.commons.model.ValidateResponse;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.reflect.Whitebox.setInternalState;

public class ValidateContollerTest {

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

        ValidateController validateController = new ValidateController();
        AuthClient authClient = mock(AuthClient.class);
        doReturn(validateResponse).when(authClient).validate(testToken);
        setInternalState(validateController, "authClient", authClient);

        // when
        ValidateRequest validateRequest = new ValidateRequest(testToken);
        ValidateResponse result = validateController.validate(validateRequest);

        // then
        assertTrue(result.isSuccess());
        assertEquals(testMessage, result.getMessage());
        assertEquals(testUser, result.getUsername());
    }
}
