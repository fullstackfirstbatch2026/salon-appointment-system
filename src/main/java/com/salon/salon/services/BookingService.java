package com.salon.salon.services;

import com.salon.salon.model.Appointment;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BookingService {

    @PersistenceContext
    private EntityManager entityManager;


    /*
     * CALL MYSQL PROCEDURE
     *
     * BookAppointment(...)
     */
    @Transactional
    public void bookAppointment(
            Appointment appointment) {

        entityManager
                .createNativeQuery(
                        "CALL BookAppointment(" +
                                ":customerId," +
                                ":stylistId," +
                                ":serviceId," +
                                ":appointmentDate," +
                                ":appointmentTime)"
                )

                .setParameter(
                        "customerId",
                        appointment.getCustomerId()
                )

                .setParameter(
                        "stylistId",
                        appointment.getStylistId()
                )

                .setParameter(
                        "serviceId",
                        appointment.getServiceId()
                )

                .setParameter(
                        "appointmentDate",
                        appointment.getAppointmentDate()
                )

                .setParameter(
                        "appointmentTime",
                        appointment.getAppointmentTime()
                )

                .executeUpdate();
    }


    /*
     * CALL MYSQL FUNCTION
     *
     * CalculateServiceBill(...)
     */
    public BigDecimal calculateBill(
            Integer serviceId) {

        Object result =
                entityManager
                        .createNativeQuery(
                                "SELECT CalculateServiceBill(:serviceId)"
                        )

                        .setParameter(
                                "serviceId",
                                serviceId
                        )

                        .getSingleResult();


        return new BigDecimal(
                result.toString()
        );
    }
}