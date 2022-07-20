package com.devsuperior.dslearn.repositories;

import com.devsuperior.dslearn.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u " +
            "JOIN FETCH u.roles " +
            "where u.email = :email")
    Optional<User> findByEmail(String email);
}