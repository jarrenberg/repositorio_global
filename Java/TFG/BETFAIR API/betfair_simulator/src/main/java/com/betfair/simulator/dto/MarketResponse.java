package com.betfair.simulator.dto;

import com.betfair.simulator.model.Market;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MarketResponse {
    @JsonProperty("marketCatalog")
    private List<Market> marketCatalog;
    
    @JsonProperty("error")
    private String error;
    
    @JsonProperty("success")
    private boolean success;
    
    // Constructores
    public MarketResponse() {}
    
    public MarketResponse(List<Market> marketCatalog, boolean success) {
        this.marketCatalog = marketCatalog;
        this.success = success;
    }
    
    public MarketResponse(String error) {
        this.error = error;
        this.success = false;
    }
    
    // Getters y Setters
    public List<Market> getMarketCatalog() { return marketCatalog; }
    public void setMarketCatalog(List<Market> marketCatalog) { this.marketCatalog = marketCatalog; }
    
    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
    
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
}