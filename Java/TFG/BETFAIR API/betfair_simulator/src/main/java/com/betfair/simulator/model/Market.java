package com.betfair.simulator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.List;

public class Market {
    @JsonProperty("marketId")
    private String marketId;
    
    @JsonProperty("marketName")
    private String marketName;
    
    @JsonProperty("event")
    private Event event;
    
    @JsonProperty("runners")
    private List<Runner> runners;
    
    @JsonProperty("marketStartTime")
    private Instant marketStartTime;
    
    @JsonProperty("totalMatched")
    private Double totalMatched;
    
    // Getters y Setters
    public String getMarketId() { return marketId; }
    public void setMarketId(String marketId) { this.marketId = marketId; }
    
    public String getMarketName() { return marketName; }
    public void setMarketName(String marketName) { this.marketName = marketName; }
    
    public Event getEvent() { return event; }
    public void setEvent(Event event) { this.event = event; }
    
    public List<Runner> getRunners() { return runners; }
    public void setRunners(List<Runner> runners) { this.runners = runners; }
    
    public Instant getMarketStartTime() { return marketStartTime; }
    public void setMarketStartTime(Instant marketStartTime) { this.marketStartTime = marketStartTime; }
    
    public Double getTotalMatched() { return totalMatched; }
    public void setTotalMatched(Double totalMatched) { this.totalMatched = totalMatched; }
}