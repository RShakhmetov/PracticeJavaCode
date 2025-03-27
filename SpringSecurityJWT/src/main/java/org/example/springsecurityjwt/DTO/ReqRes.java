package org.example.springsecurityjwt.DTO;

import lombok.Getter;
import lombok.Setter;
import org.example.springsecurityjwt.model.Product;

import java.util.List;

@Getter
@Setter
public class ReqRes {

    private int statusCode;
    private String error;
    private String message;
    private String expirationTime;
    private String ourUsers;
    private List<Product> products;
    private String token;
    private String name;
    private String password;
    private String role;
    private String refreshToken;

}
