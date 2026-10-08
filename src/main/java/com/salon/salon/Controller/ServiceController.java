package com.salon.salon.Controller;

import com.salon.salon.model.Services;
import com.salon.salon.repository.ServiceRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@CrossOrigin(origins = "*")
public class ServiceController {

    private final ServiceRepository repository;


    public ServiceController(
            ServiceRepository repository) {

        this.repository = repository;
    }


    @GetMapping
    public List<Services> getAllServices() {

        return repository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Services> getService(
            @PathVariable Integer id) {

        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public Services addService(
            @RequestBody Services service) {

        return repository.save(service);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(
            @PathVariable Integer id) {

        if (!repository.existsById(id)) {

            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}