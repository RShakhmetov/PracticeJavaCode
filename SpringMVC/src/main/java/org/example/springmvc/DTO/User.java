package org.example.springmvc.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    @NotNull(message = "Username must not be null")
    @NotBlank(message = "Username must`t be empty!")
    private String name;

    @Pattern(regexp = "^[\\w-.]+@[\\w-]+(\\.[\\w-]+)*\\.[a-z]{2,}$", message = "Invalid email")
    @NotBlank(message = "Email type must`t be empty!")
    private String email;
}
