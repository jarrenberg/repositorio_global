package com.betfair.simulator.service;

import com.betfair.simulator.config.BetfairConfig;
import com.betfair.simulator.dto.LoginRequest;
import com.betfair.simulator.dto.LoginResponse;
import com.betfair.simulator.util.JsonUtil;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class AuthService {
	private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

	private static final String LOGIN_URL = "https://identitysso.betfair.com/api/login";
	private static final String KEEP_ALIVE_URL = "https://identitysso.betfair.com/api/keepAlive";

	private final OkHttpClient httpClient;
	private String sessionToken;

	// Actualizar las URLs para usar la configuración:
	private final String loginUrl;
	private final String keepAliveUrl;

	public AuthService() {
	    this.httpClient = new OkHttpClient();
	    this.loginUrl = BetfairConfig.getIdentityUrl() + "/login";
	    this.keepAliveUrl = BetfairConfig.getIdentityUrl() + "/keepAlive";
	}

	public boolean login(String username, String password, String appKey) {
		try {
			LoginRequest loginRequest = new LoginRequest(username, password, appKey);
			String jsonBody = JsonUtil.toJson(loginRequest);

			RequestBody body = RequestBody.create(jsonBody, MediaType.parse("application/x-www-form-urlencoded"));

			Request request = new Request.Builder().url(LOGIN_URL).post(body).addHeader("X-Application", appKey)
					.addHeader("Content-Type", "application/x-www-form-urlencoded")
					.addHeader("Accept", "application/json").build();

			try (Response response = httpClient.newCall(request).execute()) {
				if (response.isSuccessful() && response.body() != null) {
					String responseBody = response.body().string();
					LoginResponse loginResponse = JsonUtil.fromJson(responseBody, LoginResponse.class);

					if (loginResponse != null && loginResponse.isSuccess()) {
						this.sessionToken = loginResponse.getToken();
						logger.info("Login exitoso");
						return true;
					} else {
						logger.error("Error en login: {}",
								loginResponse != null ? loginResponse.getError() : "Unknown error");
					}
				} else {
					logger.error("Error HTTP: {}", response.code());
				}
			}
		} catch (IOException e) {
			logger.error("Error durante el login: {}", e.getMessage());
		}

		return false;
	}

	public boolean keepAlive() {
		if (sessionToken == null) {
			logger.error("No hay sesión activa");
			return false;
		}

		try {
			Request request = new Request.Builder().url(KEEP_ALIVE_URL).get()
					.addHeader("X-Authentication", sessionToken).addHeader("Accept", "application/json").build();

			try (Response response = httpClient.newCall(request).execute()) {
				return response.isSuccessful();
			}
		} catch (IOException e) {
			logger.error("Error en keepAlive: {}", e.getMessage());
			return false;
		}
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void logout() {
		this.sessionToken = null;
		logger.info("Sesión cerrada");
	}
}