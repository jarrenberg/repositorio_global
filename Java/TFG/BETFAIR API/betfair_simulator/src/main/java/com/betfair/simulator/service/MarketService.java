package com.betfair.simulator.service;

import com.betfair.simulator.dto.MarketResponse;
import com.betfair.simulator.model.Market;
import com.betfair.simulator.model.Runner;
import com.betfair.simulator.util.JsonUtil;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

public class MarketService {
    private static final Logger logger = LoggerFactory.getLogger(MarketService.class);
    
    private static final String API_BASE_URL = "https://api.betfair.com/exchange/betting/rest/v1.0";
    
    private final AuthService authService;
    private final OkHttpClient httpClient;
    private final String appKey;
    
    public MarketService(AuthService authService, String appKey) {
        this.authService = authService;
        this.httpClient = new OkHttpClient();
        this.appKey = appKey;
    }
    
    public MarketResponse listMarketCatalogue(String eventTypeId, String marketType) {
        if (authService.getSessionToken() == null) {
            return new MarketResponse("No hay sesión activa. Por favor, inicia sesión primero.");
        }
        
        try {
            // En producción, aquí construirías el request real para la API de Betfair
            String requestBody = buildMarketCatalogueRequest(eventTypeId, marketType);
            
            Request request = new Request.Builder()
                .url(API_BASE_URL + "/listMarketCatalogue/")
                .post(RequestBody.create(requestBody, MediaType.parse("application/json")))
                .addHeader("X-Application", appKey)
                .addHeader("X-Authentication", authService.getSessionToken())
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();
            
            try (Response response = httpClient.newCall(request).execute()) {
                if (response.isSuccessful() && response.body() != null) {
                    String responseBody = response.body().string();
                    // En producción, procesarías la respuesta real
                    return processMarketCatalogueResponse(responseBody);
                } else {
                    logger.error("Error en la respuesta HTTP: {}", response.code());
                    return new MarketResponse("Error en la conexión con Betfair: " + response.code());
                }
            }
        } catch (IOException e) {
            logger.error("Error al obtener catálogo de mercados: {}", e.getMessage());
            return new MarketResponse("Error de conexión: " + e.getMessage());
        }
    }
    
    public MarketResponse getMarketOdds(String marketId) {
        if (authService.getSessionToken() == null) {
            return new MarketResponse("No hay sesión activa");
        }
        
        try {
            String requestBody = buildMarketOddsRequest(marketId);
            
            Request request = new Request.Builder()
                .url(API_BASE_URL + "/listMarketBook/")
                .post(RequestBody.create(requestBody, MediaType.parse("application/json")))
                .addHeader("X-Application", appKey)
                .addHeader("X-Authentication", authService.getSessionToken())
                .addHeader("Content-Type", "application/json")
                .build();
            
            try (Response response = httpClient.newCall(request).execute()) {
                if (response.isSuccessful() && response.body() != null) {
                    String responseBody = response.body().string();
                    return processMarketOddsResponse(marketId, responseBody);
                } else {
                    return new MarketResponse("Error HTTP: " + response.code());
                }
            }
        } catch (IOException e) {
            logger.error("Error al obtener odds del mercado: {}", e.getMessage());
            return new MarketResponse("Error de conexión: " + e.getMessage());
        }
    }
    
    private String buildMarketCatalogueRequest(String eventTypeId, String marketType) {
        // Simulación de request para mercado de fútbol
        Map<String, Object> request = new HashMap<>();
        request.put("filter", Map.of(
            "eventTypeIds", Arrays.asList(eventTypeId),
            "marketCountries", Arrays.asList("GB", "ES"),
            "marketTypeCodes", Arrays.asList(marketType)
        ));
        request.put("maxResults", 10);
        request.put("marketProjection", Arrays.asList("EVENT", "MARKET_DESCRIPTION", "RUNNER_DESCRIPTION"));
        
        return JsonUtil.toJson(request);
    }
    
    private String buildMarketOddsRequest(String marketId) {
        Map<String, Object> request = new HashMap<>();
        request.put("marketIds", Arrays.asList(marketId));
        request.put("priceProjection", Map.of(
            "priceData", Arrays.asList("EX_BEST_OFFERS"),
            "exBestOffersOverrides", Map.of(
                "bestPricesDepth", 2,
                "rollupModel", "STAKE",
                "rollupLimit", 20
            )
        ));
        request.put("orderProjection", "ALL");
        request.put("matchProjection", "ROLLED_UP_BY_PRICE");
        
        return JsonUtil.toJson(request);
    }
    
    private MarketResponse processMarketCatalogueResponse(String responseBody) {
        // En producción, procesarías la respuesta real de Betfair
        // Por ahora, simulamos datos de mercado
        
        List<Market> markets = createSimulatedMarkets();
        return new MarketResponse(markets, true);
    }
    
    private MarketResponse processMarketOddsResponse(String marketId, String responseBody) {
        // Simulación de procesamiento de odds
        // En producción, procesarías la respuesta real
        
        Market market = findMarketById(marketId);
        if (market != null) {
            return new MarketResponse(Arrays.asList(market), true);
        }
        
        return new MarketResponse("Mercado no encontrado");
    }
    
    private List<Market> createSimulatedMarkets() {
        List<Market> markets = new ArrayList<>();
        
        // Mercado 1: Fútbol - La Liga
        Market market1 = new Market();
        market1.setMarketId("1.123456789");
        market1.setMarketName("Match Odds");
        
        com.betfair.simulator.model.Event event1 = new com.betfair.simulator.model.Event();
        event1.setEventId("12345");
        event1.setEventName("Real Madrid vs Barcelona");
        event1.setCountryCode("ES");
        market1.setEvent(event1);
        
        List<Runner> runners1 = Arrays.asList(
            createRunner(1L, "Real Madrid", 2.1),
            createRunner(2L, "Barcelona", 3.2),
            createRunner(3L, "Draw", 3.5)
        );
        market1.setRunners(runners1);
        markets.add(market1);
        
        // Mercado 2: Fútbol - Premier League
        Market market2 = new Market();
        market2.setMarketId("1.987654321");
        market2.setMarketName("Match Odds");
        
        com.betfair.simulator.model.Event event2 = new com.betfair.simulator.model.Event();
        event2.setEventId("67890");
        event2.setEventName("Manchester United vs Liverpool");
        event2.setCountryCode("GB");
        market2.setEvent(event2);
        
        List<Runner> runners2 = Arrays.asList(
            createRunner(4L, "Manchester United", 2.8),
            createRunner(5L, "Liverpool", 2.4),
            createRunner(6L, "Draw", 3.3)
        );
        market2.setRunners(runners2);
        markets.add(market2);
        
        // Mercado 3: Fútbol - Champions League
        Market market3 = new Market();
        market3.setMarketId("1.555555555");
        market3.setMarketName("Match Odds");
        
        com.betfair.simulator.model.Event event3 = new com.betfair.simulator.model.Event();
        event3.setEventId("11111");
        event3.setEventName("Bayern Munich vs PSG");
        event3.setCountryCode("DE");
        market3.setEvent(event3);
        
        List<Runner> runners3 = Arrays.asList(
            createRunner(7L, "Bayern Munich", 1.9),
            createRunner(8L, "PSG", 3.8),
            createRunner(9L, "Draw", 3.6)
        );
        market3.setRunners(runners3);
        markets.add(market3);
        
        return markets;
    }
    
    private Market findMarketById(String marketId) {
        List<Market> markets = createSimulatedMarkets();
        return markets.stream()
            .filter(m -> m.getMarketId().equals(marketId))
            .findFirst()
            .orElse(null);
    }
    
    private Runner createRunner(Long selectionId, String name, Double odds) {
        Runner runner = new Runner();
        runner.setSelectionId(selectionId);
        runner.setRunnerName(name);
        runner.setOdds(odds);
        runner.setStatus("ACTIVE");
        return runner;
    }
    
    public List<String> getAvailableEventTypes() {
        return Arrays.asList(
            "1 - Fútbol",
            "2 - Tenis", 
            "7 - Carreras de Caballos",
            "4339 - Baloncesto"
        );
    }
    
    public List<String> getAvailableMarketTypes() {
        return Arrays.asList(
            "MATCH_ODDS",
            "OVER_UNDER_25",
            "ASIAN_HANDICAP",
            "CORRECT_SCORE"
        );
    }
}