package org.example.oauth.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class LogoutController {

    private final LogoutSuccessHandler logoutSuccessHandler;

    @Value("${github.client-id}")
    private String clientId;

    @Value("${github.client-secret}")
    private String clientSecret;

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
            OAuth2User oAuth2User = oAuth2AuthenticationToken.getPrincipal();

            String accessToken = (String) oAuth2User.getAttributes().get("access_token");

            if (accessToken != null) {
                revokeGitHubAccessToken(accessToken);
            }
        }
        logoutSuccessHandler.onLogoutSuccess(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/";
    }

    private void revokeGitHubAccessToken(String accessToken) {
        String url = "https://api.github.com/applications/" + clientId + "/tokens/" + accessToken;

        // Настройка заголовков для Basic Auth
        HttpHeaders headers = new HttpHeaders();
        String auth = clientId + ":" + clientSecret;
        headers.set("Authorization", "Basic " + java.util.Base64.getEncoder().encodeToString(auth.getBytes()));

        // Создание запроса
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Используем RestTemplate для отправки запроса
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Token revoked successfully!");
        } else {
            System.out.println("Failed to revoke token. Status: " + response.getStatusCode());
        }
    }
}
