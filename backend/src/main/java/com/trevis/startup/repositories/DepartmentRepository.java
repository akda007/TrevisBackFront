package com.trevis.startup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trevis.startup.entities.DepartmentData;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentData, Long> {}
