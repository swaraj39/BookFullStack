package com.micro_assignment.borrow_service.Interfaces;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "BOOK-SERVICE")
public interface FeignInterfaces {

    @PutMapping("/updatestock/dec/{id}")
    ResponseEntity<?> decrement(@PathVariable("id") Long id);
}
