package com.example.demo.Controllers;


import com.example.demo.Interfaces.FeignInterface;
import com.example.demo.Model.Books;
import com.example.demo.Repository.BooksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@CrossOrigin("*")
public class FrontController {

    @Autowired
    private BooksRepo booksRepo;

    @Autowired
    private FeignInterface feignInterface;

    // ADDING NEW BOOKS
    @PostMapping("/addBook")
    public ResponseEntity<Books> addBook(@RequestBody Books book) {
        Books savedBook = booksRepo.save(book);
        return ResponseEntity.ok(savedBook);
    }

    // GETTING ALL THE BOOKS
    @GetMapping("/getBooks")
    public ResponseEntity<List<Books>> getAllBooks() {
        List<Books> books = booksRepo.findAll();
        return ResponseEntity.ok(books);
    }

    @PutMapping("/updateBook/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id,
                                        @RequestBody Books books) {

        return booksRepo.findById(id)
                .map(existingBook -> {
                    existingBook.setName(books.getName());
                    existingBook.setStock(books.getStock());
                    existingBook.setTrend(books.getTrend());
                    existingBook.setWriter(books.getWriter());

                    booksRepo.save(existingBook);
                    return ResponseEntity.ok(existingBook);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/updatestock/dec/{id}")
    public ResponseEntity<?> decrement(@PathVariable("id") Long id){
        Optional<Books> b = booksRepo.findById(id).map(
                existingBook -> {
                    existingBook.setStock(existingBook.getStock() - 1);
                    return booksRepo.save(existingBook);
                }
        );
        return ResponseEntity.ok(b);
    }
    @PutMapping("/updatestock/incre/{id}")
    public ResponseEntity<?> increment(@PathVariable("id") Long id){
        Optional<Books> b = booksRepo.findById(id).map(
                existingBook -> {
                    existingBook.setStock(existingBook.getStock() + 1);
                    return booksRepo.save(existingBook);
                }
        );
        return ResponseEntity.ok(b);
    }


    @GetMapping("/getbor")
    public ResponseEntity<?> getall(){
        System.out.printf("calling");
        return feignInterface.getAllBorrows();
    }
}
