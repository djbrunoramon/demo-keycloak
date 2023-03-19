package br.com.brsystem.demokeycloak.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {

    @Value("${keycloak.config.realm}")
    private String realm;

    @Value("${keycloak.server-url}")
    private String serverUrl;

    @Value("${keycloak.config.client-id}")
    private String clientId;

    @Value("${keycloak.config.client-id-auth-user}")
    private String clientIdAuthenticUser;

    @Value("${keycloak.config.client-secret}")
    private String clientSecret;

    public RealmResource getKeycloakRealmInstance() {
        Keycloak keycloak = this.getInstance();
        System.out.println(keycloak.tokenManager().getAccessToken().getToken());
        return keycloak.realm(realm);
    }

    public boolean checkUserCredentials(String username, String password) {
        try {
            this.getInstance(username, password).tokenManager().getAccessToken();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Keycloak getInstance() {
        return KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .build();
    }

    private Keycloak getInstance(String username, String password) {
        return KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .clientId(clientIdAuthenticUser)
                .username(username)
                .password(password)
                .build();
    }
}
