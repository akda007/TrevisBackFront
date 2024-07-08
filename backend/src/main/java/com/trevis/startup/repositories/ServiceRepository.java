package com.trevis.startup.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trevis.startup.entities.ServiceData;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceData, Long>{
    List<ServiceData> findByNameContaining(String name);
}
