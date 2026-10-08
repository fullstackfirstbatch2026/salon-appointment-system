package com.salon.salon.repository;

import com.salon.salon.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository
        extends JpaRepository<Appointment, Integer> {

}