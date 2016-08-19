package com.furkan.sso.auth.dao;

import com.furkan.sso.auth.entity.ActiveUser;

import java.util.ArrayList;
import java.util.List;

public class ActiveUserDaoDummy implements ActiveUserDao {

    @Override
    public List<ActiveUser> findByToken(String token) {
        List<ActiveUser> activeUsers = new ArrayList<>();
        ActiveUser testActiveUser = new ActiveUser();
        testActiveUser.setId(1);
        testActiveUser.setToken(token);
        testActiveUser.setCredentials("testUser");
        activeUsers.add(testActiveUser);
        return activeUsers;
    }

    @Override
    public List<ActiveUser> findByCredentials(String credentials) {
        List<ActiveUser> activeUsers = new ArrayList<>();
        ActiveUser testActiveUser = new ActiveUser();
        testActiveUser.setId(1);
        testActiveUser.setToken("testToken");
        testActiveUser.setCredentials(credentials);
        activeUsers.add(testActiveUser);
        return activeUsers;
    }

    @Override
    public <S extends ActiveUser> S save(S entity) {
        return null;
    }

    @Override
    public <S extends ActiveUser> Iterable<S> save(Iterable<S> entities) {
        return null;
    }

    @Override
    public ActiveUser findOne(Long aLong) {
        return null;
    }

    @Override
    public boolean exists(Long aLong) {
        return false;
    }

    @Override
    public Iterable<ActiveUser> findAll() {
        return null;
    }

    @Override
    public Iterable<ActiveUser> findAll(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void delete(ActiveUser entity) {

    }

    @Override
    public void delete(Iterable<? extends ActiveUser> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
