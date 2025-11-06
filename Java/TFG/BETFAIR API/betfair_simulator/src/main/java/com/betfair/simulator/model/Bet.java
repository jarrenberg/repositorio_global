package com.betfair.simulator.model;

import java.time.LocalDateTime;

public class Bet {
    private String betId;
    private String marketId;
    private Long selectionId;
    private Double stake;
    private Double odds;
    private BetType betType;
    private LocalDateTime placedAt;
    private BetStatus status;
    
    public enum BetType {
        BACK, LAY
    }
    
    public enum BetStatus {
        PENDING, MATCHED, CANCELLED, LOST, WON
    }
    
    // Constructor
    public Bet(String marketId, Long selectionId, Double stake, Double odds, BetType betType) {
        this.betId = java.util.UUID.randomUUID().toString();
        this.marketId = marketId;
        this.selectionId = selectionId;
        this.stake = stake;
        this.odds = odds;
        this.betType = betType;
        this.placedAt = LocalDateTime.now();
        this.status = BetStatus.PENDING;
    }
    
    // Getters y Setters
    public String getBetId() { return betId; }
    public String getMarketId() { return marketId; }
    public Long getSelectionId() { return selectionId; }
    public Double getStake() { return stake; }
    public Double getOdds() { return odds; }
    public BetType getBetType() { return betType; }
    public LocalDateTime getPlacedAt() { return placedAt; }
    public BetStatus getStatus() { return status; }
    public void setStatus(BetStatus status) { this.status = status; }
    
    public Double getPotentialReturn() {
        return stake * odds;
    }
    
    public Double getPotentialProfit() {
        return getPotentialReturn() - stake;
    }
}