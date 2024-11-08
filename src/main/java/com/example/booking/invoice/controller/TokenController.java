package com.example.booking.invoice.controller;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TokenController {

//    private final OAuth2AuthorizedClientService authorizedClientService;
//
//    public TokenController(OAuth2AuthorizedClientService authorizedClientService) {
//        this.authorizedClientService = authorizedClientService;
//    }

    @GetMapping("/token")
    public String getToken(@RegisteredOAuth2AuthorizedClient("github") OAuth2AuthorizedClient authorizedClient) {
        return "Access Token: " + authorizedClient.getAccessToken().getTokenValue();
    }
}
