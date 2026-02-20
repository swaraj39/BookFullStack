package com.micro_assignment.borrow_service.service;


import org.springframework.stereotype.Service;

import com.micro_assignment.borrow_service.model.Borrow;
import com.micro_assignment.borrow_service.repository.BorrowRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowService {

    private final BorrowRepository borrowRepository;

    public BorrowService(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
    }

    public Borrow createBorrow(Borrow borrow) {
        System.out.printf(borrow.toString());
        borrow.setBorrowDate(LocalDate.now());
        borrow.setReturnDate(borrow.getBorrowDate().plusDays(7));
        System.out.printf("Saved");
        return borrowRepository.save(borrow);
    }

    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

    public Borrow getBorrowById(Long id) {
        return borrowRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Borrow record not found"));
    }

    public Borrow returnBook(Long id) {
        Borrow borrow = getBorrowById(id);
        borrow.setReturnDate(LocalDate.now());
        return borrowRepository.save(borrow);
    }

    public Borrow updateBorrow(Long id, Borrow updatedBorrow) {
        Borrow existing = getBorrowById(id);

        existing.setMemberId(updatedBorrow.getMemberId());
        existing.setBookId(updatedBorrow.getBookId());
        existing.setReturnDate(updatedBorrow.getReturnDate());

        return borrowRepository.save(existing);
    }

    public void deleteBorrow(Long id) {
        borrowRepository.deleteById(id);
    }
}
