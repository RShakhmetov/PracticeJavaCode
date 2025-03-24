package org.example.springmvcmapper.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @NotNull(message = "firstName must`t be empty")
    private String firstName;

    @NotNull(message = "lastName must`t be empty")
    private String lastName;

    @NotNull(message = "email must`t be empty")
    @Email(message = "email is not valid")
    private String email;

    @NotNull(message = "contact number must`t be empty")
    private String contactNumber;
}
