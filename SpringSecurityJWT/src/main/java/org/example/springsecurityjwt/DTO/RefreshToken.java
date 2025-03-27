package org.example.springsecurityjwt.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshToken {

    private String token;

    private String refreshToken;

    private String expirationTime;

    private int statusCode;

    private String message;
}
