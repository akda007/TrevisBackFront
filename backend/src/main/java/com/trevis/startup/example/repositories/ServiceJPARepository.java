package com.trevis.startup.example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trevis.startup.example.model.ServiceData;

@Repository
public interface ServiceJPARepository extends JpaRepository<ServiceData, Long>{
    @Override
    List<ServiceData> findAll();
    List<ServiceData> findByNameContaining(String name);
}
