package org.example.springsecurityjwt.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequest {

    private String name;

    private String password;
}
