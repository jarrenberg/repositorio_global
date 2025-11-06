package com.betfair.simulator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest {
    @JsonProperty("username")
    private String username;
    
    @JsonProperty("password")
    private String password;
    
    @JsonProperty("appKey")
    private String appKey;
    
    public LoginRequest(String username, String password, String appKey) {
        this.username = username;
        this.password = password;
        this.appKey = appKey;
    }
    
    // Getters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getAppKey() { return appKey; }
}