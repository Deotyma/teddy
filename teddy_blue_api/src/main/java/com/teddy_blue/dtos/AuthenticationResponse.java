package com.teddy_blue.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationResponse {
    
    @JsonProperty("access_token")
    private String token;

    AuthenticationResponse(String accessToken) {
      this.token = accessToken;
    }

    public String getAccessToken() {
      return token;
    }

    public void setAccessToken(String accessToken) {
      this.token = accessToken;
    }

    public static AuthenticationResponseBuilder builder() {
      return new AuthenticationResponseBuilder();
    }

    public static class AuthenticationResponseBuilder {
      private String accessToken;

      AuthenticationResponseBuilder() {}

      public AuthenticationResponseBuilder accessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
      }

      public AuthenticationResponse build() {
        return new AuthenticationResponse(accessToken);
      }
    }
  }