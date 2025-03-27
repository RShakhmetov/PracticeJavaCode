package org.example.springsecurityjwt.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInResponse {

    private int statusCode;

    private String message;

    private String token;

    private String refreshToken;

    private String expirationTime;
}
