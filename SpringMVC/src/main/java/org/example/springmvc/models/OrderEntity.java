package org.example.springmvc.models;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @JsonView(UserEntity.UserDetails.class)
    private Long cost;

    @JsonView(UserEntity.UserDetails.class)
    private String status;

    @ManyToOne
    private UserEntity user;
}
