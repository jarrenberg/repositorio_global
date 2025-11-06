package com.betfair.simulator;

import com.betfair.simulator.model.Bet;
import com.betfair.simulator.model.Market;
import com.betfair.simulator.service.BetfairService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BetfairServiceTest {
    private BetfairService betfairService;
    
    @BeforeEach
    void setUp() {
        betfairService = new BetfairService("test-app-key");
    }
    
    @Test
    void testListFootballMarkets() {
        List<Market> markets = BetfairService.listFootballMarkets();
        assertNotNull(markets);
        assertFalse(markets.isEmpty());
    }
    
    @Test
    void testPlaceBet() {
        Bet bet = betfairService.placeBet("1.123", 1L, 10.0, 2.0, Bet.BetType.BACK);
        assertNotNull(bet);
        assertEquals("1.123", bet.getMarketId());
        assertEquals(1L, bet.getSelectionId());
        assertEquals(10.0, bet.getStake());
        assertEquals(2.0, bet.getOdds());
    }
    
    @Test
    void testGetBetHistory() {
        betfairService.placeBet("1.123", 1L, 10.0, 2.0, Bet.BetType.BACK);
        List<Bet> bets = betfairService.getBetHistory();
        assertFalse(bets.isEmpty());
    }
}*/