package com.furkan.sso.auth.dao;

import com.furkan.sso.auth.entity.ActiveUser;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class ActiveUserDaoTest {

    @Test
    public void testFindByToken() {
        // given
        String testToken = "testToken";
        ActiveUserDao activeUserDao = new ActiveUserDaoDummy();

        // when
        List<ActiveUser> activeUsers = activeUserDao.findByToken(testToken);

        // then
        assertTrue(activeUsers.size() == 1);
        assertEquals(testToken, activeUsers.get(0).getToken());
    }

    @Test
    public void testFindByCredentials() {
        // given
        String testUser = "testUser";
        ActiveUserDao activeUserDao = new ActiveUserDaoDummy();

        // when
        List<ActiveUser> activeUsers = activeUserDao.findByToken(testUser);

        // then
        assertTrue(activeUsers.size() == 1);
        assertEquals(testUser, activeUsers.get(0).getCredentials());
    }

}
