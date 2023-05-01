package com.example.ticketsservice.controller;

import com.example.ticketsservice.dto.Auth0Response;
import com.example.ticketsservice.dto.TokenResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class AuthorizationController {

    private final RestTemplate restTemplate;
    private final String GRANT_TYPE;
    private final String SCOPE;
    private final String CLIENT_ID;
    private final String CLIENT_SECRET;
    private final String ISSUER_URI_TOKEN_URL;
    public AuthorizationController(RestTemplate restTemplate,
                                   @Value("${auth0.grant-type}") String grantType,
                                   @Value("${auth0.scope}") String scope,
                                   @Value("${auth0.client-id}") String clientId,
                                   @Value("${auth0.client-secret}") String clientSecret,
                                   @Value("${auth0.issuer-uri.token-url}") String issuerUriTokenUrl) {
        this.restTemplate = restTemplate;
        GRANT_TYPE = grantType;
        SCOPE = scope;
        CLIENT_ID = clientId;
        CLIENT_SECRET = clientSecret;
        ISSUER_URI_TOKEN_URL = issuerUriTokenUrl;
    }

    @GetMapping("/authorize")
    public ResponseEntity<TokenResponse> getToken(HttpServletRequest request){
        String[] credentials = extractCredentials(request);
        String username= "";
        String password = "";
        if (credentials != null) {
             username = credentials[0];
             password = credentials[1];
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", GRANT_TYPE);
        params.add("username", username);
        params.add("password", password);
        params.add("scope", SCOPE);
        params.add("client_id", CLIENT_ID);
        params.add("client_secret", CLIENT_SECRET);
        HttpEntity<MultiValueMap<String, String>> requestForToken = new HttpEntity<>(params, headers);
        ResponseEntity<Auth0Response> response = restTemplate.exchange(ISSUER_URI_TOKEN_URL, HttpMethod.POST, requestForToken, Auth0Response.class);
        return new ResponseEntity<>(new TokenResponse(response.getBody().getIdToken()), HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test(Authentication authentication){
        ((JwtAuthenticationToken) authentication).getToken().getClaims().get("nickname");
        authentication.getCredentials();
        return "test";
    }

    private String[] extractCredentials(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Basic ")) {
            String base64Credentials = authorizationHeader.substring("Basic ".length()).trim();
            byte[] decoded = Base64.getDecoder().decode(base64Credentials);
            String credentials = new String(decoded, StandardCharsets.UTF_8);
            return credentials.split(":", 2);
        }
        return null;
    }

}
