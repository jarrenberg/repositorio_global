package ejemplo;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

// Librerías de Jackson para manejar el JSON
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

public class OddsApiFetcher {

    // --- Configuración (equivalente a las variables de Python) ---
    private static final String API_KEY = "7de6949a44813433b0be20665d4292f4";
    private static final String SPORT_KEY = "soccer_epl";
    private static final String REGIONS = "eu";
    private static final String MARKET = "h2h";
    private static final String ODDS_FORMAT = "decimal";
    
    // El cliente HTTP (se recomienda una sola instancia)
    private static final HttpClient HTTP_CLIENT = HttpClient.newBuilder().build();
    // El objeto Mapper de Jackson para convertir JSON a objetos Java
    private static final ObjectMapper MAPPER = new ObjectMapper();


    public static void main(String[] args) {
        
        // --- 1. Construir la URL con parámetros ---
        String url = buildUrl();
        System.out.println("URL de la solicitud: " + url);

        // --- 2. Realizar la Solicitud GET ---
        try {
            // Crea la solicitud
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET() // Especifica el método GET
                    .build();

            // Envía la solicitud y obtén la respuesta
            HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

            // --- Manejo de Errores HTTP (equivalente a response.raise_for_status() ) ---
            if (response.statusCode() != 200) {
                throw new IOException("Error HTTP. Código de estado: " + response.statusCode() + ", Cuerpo: " + response.body());
            }

            // --- 3. Procesar el JSON (equivalente a response.json() ) ---
            // Usamos TypeReference para indicar a Jackson que esperamos una Lista de Mapas
            List<Map<String, Object>> events = MAPPER.readValue(
                    response.body(), 
                    new TypeReference<List<Map<String, Object>>>() {}
            );

            // --- 4. Procesar los Datos ---
            System.out.println("\n--- Procesamiento de Datos ---");
            System.out.println("Número de eventos encontrados: " + events.size());

            if (!events.isEmpty()) {
                // Java utiliza "get()" para acceder a valores en un Map, similar a get() en Python
                Map<String, Object> primerEvento = events.get(0);
                
                String homeTeam = (String) primerEvento.get("home_team");
                String awayTeam = (String) primerEvento.get("away_team");
                String startTime = (String) primerEvento.get("commence_time");
                
                System.out.println("\n--- Primer Evento ---");
                System.out.println("Partido: " + homeTeam + " vs " + awayTeam);
                System.out.println("Inicio: " + startTime);

                // Iterar sobre las casas de apuestas (bookmakers)
                @SuppressWarnings("unchecked")
                List<Map<String, Object>> bookmakers = (List<Map<String, Object>>) primerEvento.get("bookmakers");
                
                if (bookmakers != null) {
                    for (Map<String, Object> bookmaker : bookmakers) {
                        String bookmakerTitle = (String) bookmaker.get("title");
                        System.out.println("\n  Casa de Apuestas: " + bookmakerTitle);

                        // Iterar sobre los mercados (markets)
                        @SuppressWarnings("unchecked")
                        List<Map<String, Object>> markets = (List<Map<String, Object>>) bookmaker.get("markets");

                        if (markets != null) {
                            for (Map<String, Object> market : markets) {
                                // Iterar sobre los resultados (outcomes)
                                @SuppressWarnings("unchecked")
                                List<Map<String, Object>> outcomes = (List<Map<String, Object>>) market.get("outcomes");
                                
                                if (outcomes != null) {
                                    for (Map<String, Object> outcome : outcomes) {
                                        String name = (String) outcome.get("name");
                                        // Las cuotas son números, Jackson las leerá como Double
                                        Double price = (Double) outcome.get("price"); 
                                        
                                        System.out.printf("    - Resultado: %s, Cuota (Odds): %.2f%n", name, price);
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } catch (IOException | InterruptedException e) {
            // Captura errores de red (IOException) o de interrupción del hilo (InterruptedException)
            System.err.println("Error al procesar la solicitud: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            // Captura otros errores, incluyendo fallos en la conversión de JSON
            System.err.println("Ocurrió un error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Construye la URL de la API incluyendo todos los parámetros.
     */
    private static String buildUrl() {
        // La URL base
        String baseUrl = String.format("https://api.the-odds-api.com/v4/sports/%s/odds", SPORT_KEY);
        
        // Parámetros de la URL
        String query = String.format("apiKey=%s&regions=%s&mkt=%s&oddsFormat=%s",
                API_KEY,
                // Es buena práctica codificar los valores de los parámetros URL
                URLEncoder.encode(REGIONS, StandardCharsets.UTF_8), 
                URLEncoder.encode(MARKET, StandardCharsets.UTF_8),
                ODDS_FORMAT);
        
        return baseUrl + "?" + query;
    }
}