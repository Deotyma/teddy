package com.teddy_blue.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationResponse {

    @JsonProperty("access_token")
    private String token;
    private Long userId;  // Ajouter un champ pour l'ID utilisateur

    // Modifier le constructeur pour inclure l'ID utilisateur
    AuthenticationResponse(String accessToken, Long userId) {
        this.token = accessToken;
        this.userId = userId;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public Long getUserId() {  // Getter pour l'ID utilisateur
        return userId;
    }

    public void setUserId(Long userId) {  // Setter pour l'ID utilisateur
        this.userId = userId;
    }

    public static AuthenticationResponseBuilder builder() {
        return new AuthenticationResponseBuilder();
    }

    public static class AuthenticationResponseBuilder {
        private String accessToken;
        private Long userId;  // Champ pour l'ID dans le constructeur

        public AuthenticationResponseBuilder accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public AuthenticationResponseBuilder userId(Long userId) {  // Méthode pour définir l'ID utilisateur
            this.userId = userId;
            return this;
        }

        public AuthenticationResponse build() {
            return new AuthenticationResponse(accessToken, userId);
        }
    }
}