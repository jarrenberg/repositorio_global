package com.betfair.simulator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse {
    @JsonProperty("token")
    private String token;
    
    @JsonProperty("product")
    private String product;
    
    @JsonProperty("status")
    private String status;
    
    @JsonProperty("error")
    private String error;
    
    // Getters y Setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    
    public String getProduct() { return product; }
    public void setProduct(String product) { this.product = product; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
    
    public boolean isSuccess() {
        return "SUCCESS".equals(status);
    }
}