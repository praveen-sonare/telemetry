package com.scooterson.telemetry;

import com.auth0.json.auth.TokenHolder;
import com.auth0.json.mgmt.users.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Component
public class Auth0Service {

    private final RestTemplate restTemplate;
    private final Auth0Config config;

    private static final String OAUTH_TOKEN_API = "/oauth/token";

    private static final String USER_API = "/api/v2/users";
    @Autowired
    Auth0Service(RestTemplate restTemplate, Auth0Config config){
        this.restTemplate = restTemplate;
        this.config = config;
    }

    public void createUser(String email, String password){
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Bearer "+getAuthorizationToken());

        var authUser = new User();
        authUser.setConnection(config.getConnection());
        authUser.setEmail(email);
        authUser.setPassword(password.toCharArray());

        var request = new HttpEntity<>(authUser, headers);
        var response = restTemplate.postForEntity(config.getDomain()+ USER_API, request, String.class);
        log.info(response.getBody());
    }


    private String getAuthorizationToken() {
        var grantType = "client_credentials";
        var audience = config.getDomain() + "/api/v2/";
        var request = AuthTokenRequest.builder()
                .clientId(config.getClientId())
                .clientSecret(config.getClientSecret())
                .grantType(grantType)
                .audience(audience)
                .build();

        var response = restTemplate.postForEntity(
                config.getDomain() + OAUTH_TOKEN_API, request, TokenHolder.class);
        var body = response.getBody();
        return body.getAccessToken();

    }

}
