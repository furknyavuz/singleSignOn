package com.furkan.sso.auth.controller;

import com.furkan.sso.auth.dao.ActiveUserDao;
import com.furkan.sso.auth.dao.ActiveUserDaoDummy;
import com.furkan.sso.auth.entity.ActiveUser;
import com.furkan.sso.auth.ldap.LdapClient;
import com.furkan.sso.commons.model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.reflect.Whitebox.setInternalState;

public class AuthControllerTest {

    @Test
    public void testAuthWithoutOldSession() {
        // given
        String testUser = "testUser";
        String testPassword = "testPassword";
        String testMessage = "Authorized";

        AuthController authController = new AuthController();

        ActiveUserDao activeUserDao = mock(ActiveUserDaoDummy.class);
        doReturn(null).when(activeUserDao).findByCredentials(testUser);
        setInternalState(authController, "activeUserDao", activeUserDao);

        LdapClient ldapClient = mock(LdapClient.class);
        doReturn(true).when(ldapClient).authenticateWithUsernameAndPassword(testUser, testPassword);
        setInternalState(authController, "ldapClient", ldapClient);

        // when
        AuthRequest authRequest = new AuthRequest(testUser, testPassword);
        AuthResponse result = authController.auth(authRequest);

        // then
        assertTrue(result.isSuccess());
        assertEquals(testMessage, result.getMessage());
        assertEquals(testUser, result.getUsername());
    }

    @Test
    public void testAuthWithOldSession() {
        // given
        String testToken = "qqn4pc1vjul8k223c20uqrne8r";
        String testUser = "testUser";
        String testPassword = "testPassword";
        String testMessage = "Authorized";
        ActiveUser testActiveUser = new ActiveUser();
        testActiveUser.setId(1);
        testActiveUser.setToken(testToken);
        testActiveUser.setCredentials(testUser);

        AuthController authController = new AuthController();

        List<ActiveUser> activeUsers = new ArrayList<>();
        activeUsers.add(testActiveUser);
        ActiveUserDao activeUserDao = mock(ActiveUserDaoDummy.class);
        doReturn(activeUsers).when(activeUserDao).findByCredentials(testUser);
        setInternalState(authController, "activeUserDao", activeUserDao);

        LdapClient ldapClient = mock(LdapClient.class);
        doReturn(true).when(ldapClient).authenticateWithUsernameAndPassword(testUser, testPassword);
        setInternalState(authController, "ldapClient", ldapClient);

        // when
        AuthRequest authRequest = new AuthRequest(testUser, testPassword);
        AuthResponse result = authController.auth(authRequest);

        // then
        assertTrue(result.isSuccess());
        assertEquals(testMessage, result.getMessage());
        assertEquals(testUser, result.getUsername());
        assertEquals(testToken, result.getToken());
    }

    @Test
    public void testValidate() {
        // given
        String testToken = "qqn4pc1vjul8k223c20uqrne8r";
        String testUser = "testUser";
        String testMessage = "Authorized";
        ActiveUser testActiveUser = new ActiveUser();
        testActiveUser.setId(1);
        testActiveUser.setToken(testToken);
        testActiveUser.setCredentials(testUser);

        AuthController authController = new AuthController();

        List<ActiveUser> activeUsers = new ArrayList<>();
        activeUsers.add(testActiveUser);
        ActiveUserDao activeUserDao = mock(ActiveUserDaoDummy.class);
        doReturn(activeUsers).when(activeUserDao).findByToken(testToken);
        setInternalState(authController, "activeUserDao", activeUserDao);

        // when
        ValidateRequest validateRequest = new ValidateRequest(testToken);
        ValidateResponse result = authController.validate(validateRequest);

        // then
        assertTrue(result.isSuccess());
        assertEquals(testMessage, result.getMessage());
        assertEquals(testUser, result.getUsername());
    }

    @Test
    public void testInvalidate() {
        // given
        String testToken = "qqn4pc1vjul8k223c20uqrne8r";
        String testUser = "testUser";
        String testMessage = "Invalidated";
        ActiveUser testActiveUser = new ActiveUser();
        testActiveUser.setId(1);
        testActiveUser.setToken(testToken);
        testActiveUser.setCredentials(testUser);

        AuthController authController = new AuthController();

        List<ActiveUser> activeUsers = new ArrayList<>();
        activeUsers.add(testActiveUser);
        ActiveUserDao activeUserDao = mock(ActiveUserDaoDummy.class);
        doReturn(activeUsers).when(activeUserDao).findByToken(testToken);
        setInternalState(authController, "activeUserDao", activeUserDao);

        // when
        InvalidateRequest invalidateRequest = new InvalidateRequest(testToken);
        InvalidateResponse result = authController.invalidate(invalidateRequest);

        // then
        assertTrue(result.isSuccess());
        assertEquals(testMessage, result.getMessage());
    }
}
