package com.betfair.simulator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Runner {
    @JsonProperty("selectionId")
    private Long selectionId;
    
    @JsonProperty("runnerName")
    private String runnerName;
    
    @JsonProperty("odds")
    private Double odds;
    
    @JsonProperty("status")
    private String status;
    
    // Getters y Setters
    public Long getSelectionId() { return selectionId; }
    public void setSelectionId(Long selectionId) { this.selectionId = selectionId; }
    
    public String getRunnerName() { return runnerName; }
    public void setRunnerName(String runnerName) { this.runnerName = runnerName; }
    
    public Double getOdds() { return odds; }
    public void setOdds(Double odds) { this.odds = odds; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}