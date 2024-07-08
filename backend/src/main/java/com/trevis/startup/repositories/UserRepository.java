package com.trevis.startup.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trevis.startup.entities.UserData;

@Repository
public interface UserRepository extends JpaRepository<UserData, Long> {
    Optional<UserData> findByUsername(String username);
}
