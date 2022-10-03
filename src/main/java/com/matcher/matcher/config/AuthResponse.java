package com.matcher.matcher.config;

public class AuthResponse {
    private String account;
    private String accessToken;

    public AuthResponse() { }

    public AuthResponse(String account, String accessToken) {
        this.account = account;
        this.accessToken = accessToken;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
