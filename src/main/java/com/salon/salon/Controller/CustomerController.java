package com.salon.salon.Controller;

import com.salon.salon.model.Customer;
import com.salon.salon.repository.CustomerRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class CustomerController {

    private final CustomerRepository repository;


    public CustomerController(
            CustomerRepository repository) {

        this.repository = repository;
    }


    @GetMapping
    public List<Customer> getAllCustomers() {

        return repository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(
            @PathVariable Integer id) {

        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public Customer addCustomer(
            @RequestBody Customer customer) {

        return repository.save(customer);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable Integer id,
            @RequestBody Customer customer) {

        return repository
                .findById(id)
                .map(existing -> {

                    existing.setCustomerName(
                            customer.getCustomerName()
                    );

                    existing.setPhone(
                            customer.getPhone()
                    );

                    existing.setEmail(
                            customer.getEmail()
                    );

                    return ResponseEntity.ok(
                            repository.save(existing)
                    );

                })
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(
            @PathVariable Integer id) {

        if (!repository.existsById(id)) {

            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}