package com.trevis.startup.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.trevis.startup.entities.ServiceData;

@Repository
public interface ServiceRepository extends CrudRepository<ServiceData, Long>{}
