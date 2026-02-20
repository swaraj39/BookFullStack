package com.example.demo.DTO;

import java.time.LocalDate;

public class Borrow {
    private Long id;
    private Long memberId;
    private Long bookId;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public Borrow() {}

    public Long getId() { return id; }
    public Long getMemberId() { return memberId; }
    public Long getBookId() { return bookId; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getReturnDate() { return returnDate; }

    public void setId(Long id) { this.id = id; }
    public void setMemberId(Long memberId) { this.memberId = memberId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }
    public void setBorrowDate(LocalDate borrowDate) { this.borrowDate = borrowDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
}