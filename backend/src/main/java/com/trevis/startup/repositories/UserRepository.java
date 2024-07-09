package com.trevis.startup.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.trevis.startup.entities.UserData;

@Repository
public interface UserRepository extends CrudRepository<UserData, Long> {
    Optional<UserData> findByUsername(String username);
}
