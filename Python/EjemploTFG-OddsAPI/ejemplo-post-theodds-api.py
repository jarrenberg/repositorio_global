import requests
import json

# --- Configuración ---
API_KEY = '7de6949a44813433b0be20665d4292f4'
SPORT_KEY = 'soccer_epl'  # Por ejemplo, Premier League inglesa
REGIONS = 'eu'           # Cuotas de casas de apuestas europeas
MARKET = 'h2h'           # Head to Head (1X2)
ODDS_FORMAT = 'decimal'  # Formato: 2.50, 1.80, etc.

# --- Construir la URL ---
url = f"https://api.the-odds-api.com/v4/sports/{SPORT_KEY}/odds"
params = {
    'apiKey': API_KEY,
    'regions': REGIONS,
    'mkt': MARKET,
    'oddsFormat': ODDS_FORMAT
}

# --- Realizar la Solicitud GET ---
try:
    response = requests.get(url, params=params)
    response.raise_for_status()  # Lanza un error para códigos de estado HTTP malos
    
    data = response.json()
    
    # --- Procesar los Datos ---
    print(f"Número de eventos encontrados: {len(data)}")
    
    if data:
        primer_evento = data[0]
        print("\n--- Primer Evento ---")
        print(f"Partido: {primer_evento.get('home_team')} vs {primer_evento.get('away_team')}")
        print(f"Inicio: {primer_evento.get('commence_time')}")
        
        # Iterar sobre las casas de apuestas (bookmakers)
        for bookmaker in primer_evento.get('bookmakers', []):
            print(f"\n  Casa de Apuestas: {bookmaker.get('title')}")
            for market in bookmaker.get('markets', []):
                for outcome in market.get('outcomes', []):
                    # 'name' puede ser el nombre del equipo o 'Draw' (Empate)
                    print(f"    - Resultado: {outcome.get('name')}, Cuota (Odds): {outcome.get('price')}")
                    
except requests.exceptions.RequestException as e:
    print(f"Error en la solicitud: {e}")