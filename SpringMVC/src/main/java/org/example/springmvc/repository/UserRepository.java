package org.example.springmvc.repository;

import org.example.springmvc.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Boolean existsByEmail(String email);
}
