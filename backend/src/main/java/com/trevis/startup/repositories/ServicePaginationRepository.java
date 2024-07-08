package com.trevis.startup.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.trevis.startup.entities.ServiceData;
import java.util.List;

@Repository
public interface ServicePaginationRepository extends PagingAndSortingRepository<ServiceData, Long> {
    List<ServiceData> findByName(String name, Pageable pageable);
}
