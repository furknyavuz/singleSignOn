package com.furkan.sso.auth.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * ActiveUser Entity
 * Model representations of ActiveUser table in MySql DB
 */
@Entity
@Table(name = "activeusers")
public class ActiveUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String token;

    @NotNull
    private String credentials;

    public ActiveUser() {
    }

    public ActiveUser(long id) {
        this.id = id;
    }

    public ActiveUser(String token, String credentials) {
        this.token = token;
        this.credentials = credentials;
    }

    public long getId() {
        return id;
    }

    public void setId(long value) {
        this.id = value;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    @Override
    public String toString() {
        return "ActiveUser{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", credentials='" + credentials + '\'' +
                '}';
    }
}
