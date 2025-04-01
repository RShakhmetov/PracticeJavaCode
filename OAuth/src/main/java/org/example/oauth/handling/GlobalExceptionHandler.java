package org.example.oauth.handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Ошибка аутентификации: " + ex.getMessage());
    }

    @ExceptionHandler(OAuth2AuthenticationException.class)
    public ResponseEntity<String> handleOAuth2AuthenticationException(OAuth2AuthenticationException ex) {
        if (isTokenExpired(ex)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Ваш токен истек, пожалуйста, авторизуйтесь заново.");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Ошибка аутентификации: " + ex.getMessage());
    }

    private boolean isTokenExpired(OAuth2AuthenticationException ex) {
        return ex.getMessage().contains("expired");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Произошла ошибка: " + ex.getMessage());
    }
}
