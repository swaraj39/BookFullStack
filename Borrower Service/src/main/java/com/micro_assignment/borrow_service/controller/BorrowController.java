package com.micro_assignment.borrow_service.controller;


import com.micro_assignment.borrow_service.Interfaces.FeignInterfaces;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.micro_assignment.borrow_service.model.Borrow;
import com.micro_assignment.borrow_service.service.BorrowService;

import java.util.List;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/borrows")
public class BorrowController {

    private final BorrowService borrowService;

    @Autowired
    private FeignInterfaces feignInterfaces;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @Transactional
    @PostMapping("/new-borrow")
    public ResponseEntity<?> createBorrow(@RequestBody Borrow borrow) {
        System.out.printf("exceuting");
        Borrow created = borrowService.createBorrow(borrow);
        Long b = created.getBookId();
        feignInterfaces.decrement(b);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Borrow>> getAllBorrows() {
        return ResponseEntity.ok(borrowService.getAllBorrows());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Borrow> getBorrowById(@PathVariable Long id) {
        return ResponseEntity.ok(borrowService.getBorrowById(id));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Borrow> updateBorrow(@PathVariable Long id,
                                               @RequestBody Borrow borrow) {
        return ResponseEntity.ok(borrowService.updateBorrow(id, borrow));
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<Borrow> returnBook(@PathVariable Long id) {
        return ResponseEntity.ok(borrowService.returnBook(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBorrow(@PathVariable Long id) {
        borrowService.deleteBorrow(id);
        return ResponseEntity.ok(borrowService.getAllBorrows());
    }
}
