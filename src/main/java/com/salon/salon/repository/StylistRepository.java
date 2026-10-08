package com.salon.salon.repository;

import com.salon.salon.model.Stylist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StylistRepository
        extends JpaRepository<Stylist, Integer> {

}