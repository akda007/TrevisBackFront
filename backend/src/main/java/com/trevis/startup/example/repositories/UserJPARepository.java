package com.trevis.startup.example.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trevis.startup.example.model.UserData;


@Repository
public interface UserJPARepository extends JpaRepository<UserData, Long> {
    Optional<UserData> findByLogin(String login);
}
