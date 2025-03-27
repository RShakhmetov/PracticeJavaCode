package org.example.springsecurityjwt.repositories;

import org.example.springsecurityjwt.model.OurUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<OurUser, Long>{
    Optional<OurUser> findByUsername(String username);
    boolean existsByUsername(String name);
}
