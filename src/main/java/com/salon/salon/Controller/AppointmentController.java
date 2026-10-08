package com.salon.salon.Controller;

import com.salon.salon.model.Appointment;
import com.salon.salon.repository.AppointmentRepository;
import com.salon.salon.services.BookingService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {

    private final AppointmentRepository repository;

    private final BookingService bookingService;


    public AppointmentController(
            AppointmentRepository repository,
            BookingService bookingService) {

        this.repository = repository;

        this.bookingService =
                bookingService;
    }


    /*
     * GET ALL APPOINTMENTS
     */
    @GetMapping
    public List<Appointment> getAllAppointments() {

        return repository.findAll();
    }


    /*
     * GET APPOINTMENT BY ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointment(
            @PathVariable Integer id) {

        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(
                        ResponseEntity.notFound().build()
                );
    }


    /*
     * BOOK APPOINTMENT
     *
     * Java
     *   ↓
     * BookingService
     *   ↓
     * MySQL Procedure
     *   ↓
     * Trigger
     */
    @PostMapping
    public ResponseEntity<?> bookAppointment(
            @RequestBody Appointment appointment) {

        try {

            bookingService.bookAppointment(
                    appointment
            );

            return ResponseEntity.ok(
                    Map.of(
                            "message",
                            "Appointment booked successfully"
                    )
            );

        } catch (Exception e) {

            String message =
                    e.getMessage();

            if (message == null) {

                message =
                        "Appointment booking failed";
            }

            return ResponseEntity
                    .badRequest()
                    .body(
                            Map.of(
                                    "error",
                                    message
                            )
                    );
        }
    }


    /*
     * CALCULATE BILL
     *
     * GET:
     * /api/appointments/bill/1
     */
    @GetMapping("/bill/{serviceId}")
    public ResponseEntity<?> calculateBill(
            @PathVariable Integer serviceId) {

        try {

            BigDecimal total =
                    bookingService.calculateBill(
                            serviceId
                    );

            return ResponseEntity.ok(
                    Map.of(
                            "serviceId",
                            serviceId,

                            "totalBill",
                            total
                    )
            );

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body(
                            Map.of(
                                    "error",
                                    e.getMessage()
                            )
                    );
        }
    }


    /*
     * DELETE APPOINTMENT
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAppointment(
            @PathVariable Integer id) {

        if (!repository.existsById(id)) {

            return ResponseEntity
                    .notFound()
                    .build();
        }

        repository.deleteById(id);

        return ResponseEntity.ok(
                Map.of(
                        "message",
                        "Appointment deleted successfully"
                )
        );
    }
}