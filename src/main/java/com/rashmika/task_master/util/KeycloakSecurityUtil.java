package com.rashmika.task_master.util;

import com.rashmika.task_master.exception.AuthenticationException;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KeycloakSecurityUtil {

    private Keycloak keycloak;

    @Value("${KEYCLOAK_SERVER_URL}")
    private String serverUrl;

    @Value("${KEYCLOAK_CLIENT_ID}")
    private String clientId;

    @Value("${KEYCLOAK_CLIENT_SECRET}")
    private String clientSecret;

    @Value("${KEYCLOAK_REALM}")
    private String realm;

    @Value("${KEYCLOAK_ADMIN_USERNAME}")
    private String adminUsername;

    @Value("${KEYCLOAK_ADMIN_PASSWORD}")
    private String adminPassword;

    public AccessTokenResponse authenticateUser(String username, String password) {
        // Build a temporary Keycloak instance with the user's credentials
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .grantType(OAuth2Constants.PASSWORD)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .username(username)
                .password(password)
                .build();

        // Request an Access Token
        AccessTokenResponse tokenResponse = null;
        try {
            tokenResponse = keycloak.tokenManager().getAccessToken();
        } catch (Exception e) {
            throw new AuthenticationException("Invalid credentials or error accessing Keycloak: " + e.getMessage());
        }
        return tokenResponse;
    }

    public Keycloak getKeycloakInstance() {
        if (keycloak == null) {
            keycloak = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
                    .grantType(OAuth2Constants.PASSWORD)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .username(adminUsername)
                    .password(adminPassword)
                    .build();
        }
        return keycloak;
    }
}
