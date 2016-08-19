package com.furkan.sso.auth.ldap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.ldap.core.LdapTemplate;

import static junit.framework.TestCase.assertTrue;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest({LdapClient.class})
public class LdapClientTest {

    @Test
    public void testAuthenticateWithUsernameAndPassword() throws Exception {
        // given
        String testUser = "testUser";
        String testPassword = "testPassword";
        LdapTemplate ldapTemplate = mock(LdapTemplate.class);

        whenNew(LdapTemplate.class).withAnyArguments().thenReturn(ldapTemplate);
        LdapClient ldapClient = new LdapClient();
        doReturn(true).when(ldapTemplate).authenticate("", "(uid=" + testUser + ")", testPassword);

        // when
        boolean result = ldapClient.authenticateWithUsernameAndPassword(testUser, testPassword);

        // then
        assertTrue(result);
    }
}
