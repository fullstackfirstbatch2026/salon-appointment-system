package com.salon.salon.repository;

import com.salon.salon.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository
        extends JpaRepository<Services, Integer> {

}