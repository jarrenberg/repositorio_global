package com.betfair.simulator;

import com.betfair.simulator.config.AppConfig;
import com.betfair.simulator.dto.MarketResponse;
import com.betfair.simulator.model.Bet;
import com.betfair.simulator.model.Market;
import com.betfair.simulator.model.Runner;
import com.betfair.simulator.service.BetfairService;

import java.util.List;
import java.util.Scanner;

public class BetfairSimulatorApplication {
    private static final BetfairService betfairService = new BetfairService(AppConfig.APP_KEY);
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=== Betfair Simulator ===");
        
        // Simular login (en producción usarías credenciales reales)
        boolean loggedIn = betfairService.login(AppConfig.USERNAME, AppConfig.PASSWORD);
        
        if (!loggedIn) {
            System.out.println("Error en el login. Usando modo simulación...");
        } else {
            System.out.println("Login exitoso!");
        }
        
        showMainMenu();
    }
    
    private static void showMainMenu() {
        while (true) {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Ver mercados de fútbol");
            System.out.println("2. Hacer una apuesta");
            System.out.println("3. Ver historial de apuestas");
            System.out.println("4. Ver balance");
            System.out.println("5. Simular resultado");
            System.out.println("6. Salir");
            System.out.print("Selecciona una opción: ");
            
            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir newline
            
            switch (option) {
                case 1:
                    showFootballMarkets();
                    break;
                case 2:
                    placeBetMenu();
                    break;
                case 3:
                    showBetHistory();
                    break;
                case 4:
                    showBalance();
                    break;
                case 5:
                    simulateResult();
                    break;
                case 6:
                    System.out.println("¡Hasta luego!");
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }
    
    
    private static void placeBetMenu() {
        System.out.println("\n=== HACER APUESTA ===");
        
        System.out.print("ID del mercado: ");
        String marketId = scanner.nextLine();
        
        System.out.print("ID de la selección: ");
        Long selectionId = scanner.nextLong();
        
        System.out.print("Stake: ");
        Double stake = scanner.nextDouble();
        
        System.out.print("Cuota: ");
        Double odds = scanner.nextDouble();
        scanner.nextLine(); // Consumir newline
        
        System.out.print("Tipo (BACK/LAY): ");
        String betTypeStr = scanner.nextLine().toUpperCase();
        
        Bet.BetType betType = Bet.BetType.valueOf(betTypeStr);
        
        Bet bet = betfairService.placeBet(marketId, selectionId, stake, odds, betType);
        
        System.out.println("¡Apuesta colocada exitosamente!");
        System.out.println("ID de apuesta: " + bet.getBetId());
        System.out.println("Retorno potencial: " + bet.getPotentialReturn());
        System.out.println("Beneficio potencial: " + bet.getPotentialProfit());
    }
    
    private static void showBetHistory() {
        System.out.println("\n=== HISTORIAL DE APUESTAS ===");
        List<Bet> bets = betfairService.getBetHistory();
        
        if (bets.isEmpty()) {
            System.out.println("No hay apuestas realizadas");
            return;
        }
        
        for (Bet bet : bets) {
            System.out.println("ID: " + bet.getBetId());
            System.out.println("Mercado: " + bet.getMarketId());
            System.out.println("Selección: " + bet.getSelectionId());
            System.out.println("Stake: " + bet.getStake());
            System.out.println("Cuota: " + bet.getOdds());
            System.out.println("Tipo: " + bet.getBetType());
            System.out.println("Estado: " + bet.getStatus());
            System.out.println("Fecha: " + bet.getPlacedAt());
            System.out.println("------------------------");
        }
    }
    
    private static void showBalance() {
        double balance = betfairService.getAccountBalance();
        System.out.println("\nBalance actual: " + balance + " EUR");
    }
    
    private static void simulateResult() {
        System.out.println("\n=== SIMULAR RESULTADO ===");
        
        System.out.print("ID del mercado: ");
        String marketId = scanner.nextLine();
        
        System.out.print("ID del ganador: ");
        Long winnerId = scanner.nextLong();
        scanner.nextLine(); // Consumir newline
        
        betfairService.simulateMarketResult(marketId, winnerId);
        System.out.println("Resultado simulado para mercado " + marketId + ". Ganador: " + winnerId);
    
    }
 // En el método showFootballMarkets, actualizar para usar MarketResponse:
    private static void showFootballMarkets() {
        System.out.println("\n=== MERCADOS DE FÚTBOL ===");
        MarketResponse response = betfairService.listFootballMarkets();
        
        if (!response.isSuccess()) {
            System.out.println("Error: " + response.getError());
            return;
        }
        
        List<Market> markets = response.getMarketCatalog();
        for (int i = 0; i < markets.size(); i++) {
            Market market = markets.get(i);
            System.out.println((i + 1) + ". " + market.getEvent().getEventName());
            System.out.println("   Mercado: " + market.getMarketName());
            System.out.println("   ID: " + market.getMarketId());
            
            for (Runner runner : market.getRunners()) {
                System.out.println("   - " + runner.getRunnerName() + ": " + runner.getOdds() + 
                                 " (ID: " + runner.getSelectionId() + ")");
            }
            System.out.println();
        }
    }

    // Agregar nuevo método para mostrar tipos de eventos
    private static void showEventTypes() {
        System.out.println("\n=== TIPOS DE EVENTOS DISPONIBLES ===");
        List<String> eventTypes = betfairService.getAvailableEventTypes();
        for (String eventType : eventTypes) {
            System.out.println(" - " + eventType);
        }
    }
}