package com.salon.salon.Controller;

import com.salon.salon.model.Stylist;
import com.salon.salon.repository.StylistRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stylists")
@CrossOrigin(origins = "*")
public class StylistController {

    private final StylistRepository repository;


    public StylistController(
            StylistRepository repository) {

        this.repository = repository;
    }


    @GetMapping
    public List<Stylist> getAllStylists() {

        return repository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Stylist> getStylist(
            @PathVariable Integer id) {

        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public Stylist addStylist(
            @RequestBody Stylist stylist) {

        return repository.save(stylist);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStylist(
            @PathVariable Integer id) {

        if (!repository.existsById(id)) {

            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}