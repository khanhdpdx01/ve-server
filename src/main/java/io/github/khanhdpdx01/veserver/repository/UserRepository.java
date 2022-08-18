package io.github.khanhdpdx01.veserver.repository;

import io.github.khanhdpdx01.veserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u from User u where u.username = :username")
    Optional<User> findByUsername(String username);
}
