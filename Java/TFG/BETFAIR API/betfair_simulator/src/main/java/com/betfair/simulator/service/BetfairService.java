package com.betfair.simulator.service;

import com.betfair.simulator.config.BetfairConfig;
import com.betfair.simulator.dto.MarketResponse;
import com.betfair.simulator.model.Bet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class BetfairService {
    private static final Logger logger = LoggerFactory.getLogger(BetfairService.class);
    
    private final AuthService authService;
    private final MarketService marketService;
    private final Map<String, Bet> placedBets;
    
    public BetfairService() {
        this.authService = new AuthService();
        this.marketService = new MarketService(authService, BetfairConfig.getAppKey());
        this.placedBets = new HashMap<>();
    }
    
    public BetfairService(String appKey) {
        this.authService = new AuthService();
        this.marketService = new MarketService(authService, appKey);
        this.placedBets = new HashMap<>();
    }
    
    public boolean login(String username, String password) {
        return authService.login(username, password, BetfairConfig.getAppKey());
    }
    
    public MarketResponse listFootballMarkets() {
        return marketService.listMarketCatalogue("1", "MATCH_ODDS");
    }
    
    public MarketResponse getMarketOdds(String marketId) {
        return marketService.getMarketOdds(marketId);
    }
    
    public List<String> getAvailableEventTypes() {
        return marketService.getAvailableEventTypes();
    }
    
    public List<String> getAvailableMarketTypes() {
        return marketService.getAvailableMarketTypes();
    }
    
    // Los demás métodos permanecen igual...
    public Bet placeBet(String marketId, Long selectionId, Double stake, Double odds, Bet.BetType betType) {
        Bet bet = new Bet(marketId, selectionId, stake, odds, betType);
        placedBets.put(bet.getBetId(), bet);
        
        logger.info("Apuesta colocada: {} en {} con cuota {} - Stake: {}", 
                   betType, selectionId, odds, stake);
        
        return bet;
    }
    
    public List<Bet> getBetHistory() {
        return new ArrayList<>(placedBets.values());
    }
    
    public Bet getBet(String betId) {
        return placedBets.get(betId);
    }
    
    public boolean cancelBet(String betId) {
        Bet bet = placedBets.get(betId);
        if (bet != null && bet.getStatus() == Bet.BetStatus.PENDING) {
            bet.setStatus(Bet.BetStatus.CANCELLED);
            logger.info("Apuesta cancelada: {}", betId);
            return true;
        }
        return false;
    }
    
    public void simulateMarketResult(String marketId, Long winningSelectionId) {
        for (Bet bet : placedBets.values()) {
            if (bet.getMarketId().equals(marketId)) {
                if (bet.getSelectionId().equals(winningSelectionId)) {
                    bet.setStatus(Bet.BetStatus.WON);
                } else {
                    bet.setStatus(Bet.BetStatus.LOST);
                }
            }
        }
        logger.info("Resultado simulado para mercado {}: Ganador {}", marketId, winningSelectionId);
    }
    
    public double getAccountBalance() {
        return 1000.0; // Balance simulado
    }
    
    public AuthService getAuthService() {
        return authService;
    }
    
    public MarketService getMarketService() {
        return marketService;
    }
}