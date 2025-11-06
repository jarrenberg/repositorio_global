package com.betfair.simulator.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BetfairConfig {
    private static final Properties properties = new Properties();
    
    static {
        loadProperties();
    }
    
    private static void loadProperties() {
        try (InputStream input = BetfairConfig.class.getClassLoader()
                .getResourceAsStream("application.properties")) {
            
            if (input != null) {
                properties.load(input);
            } else {
                // Valores por defecto para desarrollo
                setDefaultProperties();
            }
        } catch (IOException e) {
            System.err.println("Error cargando configuración, usando valores por defecto: " + e.getMessage());
            setDefaultProperties();
        }
    }
    
    private static void setDefaultProperties() {
        properties.setProperty("betfair.app.key", System.getenv().getOrDefault("BETFAIR_APP_KEY", "TU_APP_KEY_AQUI"));
        properties.setProperty("betfair.username", System.getenv().getOrDefault("BETFAIR_USERNAME", "tu_usuario"));
        properties.setProperty("betfair.password", System.getenv().getOrDefault("BETFAIR_PASSWORD", "tu_contraseña"));
        properties.setProperty("betfair.api.base.url", "https://api.betfair.com/exchange/betting/rest/v1.0");
        properties.setProperty("betfair.identity.url", "https://identitysso.betfair.com/api");
        properties.setProperty("app.name", "Betfair Simulator");
        properties.setProperty("app.version", "1.0.0");
        properties.setProperty("app.max.retry.attempts", "3");
        properties.setProperty("app.request.timeout", "30");
    }
    
    // Getters para configuración de Betfair
    public static String getAppKey() {
        return properties.getProperty("betfair.app.key");
    }
    
    public static String getUsername() {
        return properties.getProperty("betfair.username");
    }
    
    public static String getPassword() {
        return properties.getProperty("betfair.password");
    }
    
    public static String getApiBaseUrl() {
        return properties.getProperty("betfair.api.base.url");
    }
    
    public static String getIdentityUrl() {
        return properties.getProperty("betfair.identity.url");
    }
    
    // Getters para configuración de la aplicación
    public static String getAppName() {
        return properties.getProperty("app.name");
    }
    
    public static String getAppVersion() {
        return properties.getProperty("app.version");
    }
    
    public static int getMaxRetryAttempts() {
        return Integer.parseInt(properties.getProperty("app.max.retry.attempts", "3"));
    }
    
    public static int getRequestTimeout() {
        return Integer.parseInt(properties.getProperty("app.request.timeout", "30"));
    }
    
    public static Properties getProperties() {
        return new Properties(properties);
    }
    
    public static void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }
}