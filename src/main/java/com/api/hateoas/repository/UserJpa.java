package com.api.hateoas.repository;

import com.api.hateoas.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserJpa extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByEmail(String email);
}
