package com.example.demo.Interfaces;


import com.example.demo.DTO.Borrow;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "BORROW-SERVICE")
public interface FeignInterface {

    @GetMapping("/borrows/all")
    ResponseEntity<List<Borrow>> getAllBorrows();
}
