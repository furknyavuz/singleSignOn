package com.furkan.sso.auth.ldap;

import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

/**
 * Ldap client contains LdapContextSource configurations and Ldap queries for authenticate
 */
public class LdapClient {

    private LdapContextSource contextSource;

    public LdapClient() {
        // LDAP Connection
        contextSource = new LdapContextSource();
        contextSource.setUrl("ldap://localhost:" + 10389);
        contextSource.setUserDn("");
        contextSource.setPassword("");
        contextSource.setPooled(false);
        contextSource.afterPropertiesSet();
    }

    /**
     * @param username : Corresponds to uid field in Ldap
     * @param password : Corresponds to userPassword field in Ldap
     * @return If authenticated true else false
     */
    public boolean authenticateWithUsernameAndPassword(String username, String password) {
        LdapTemplate ldapTemplate = new LdapTemplate(contextSource);
        return ldapTemplate.authenticate("", "(uid=" + username + ")", password);
    }
}
