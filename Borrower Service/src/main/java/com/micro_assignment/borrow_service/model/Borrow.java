package com.micro_assignment.borrow_service.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity
@Table(name = "borrows")
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "book_id", nullable = false)
    private Long bookId;

    @Column(name = "borrow_date", nullable = false)
    private LocalDate borrowDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    public Borrow() {}

    public Borrow(Long memberId, Long bookId, LocalDate borrowDate, LocalDate returnDate) {
        this.memberId = memberId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public Long getId() { return id; }
    public Long getMemberId() { return memberId; }
    public Long getBookId() { return bookId; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getReturnDate() { return returnDate; }

    @Override
    public String toString() {
        return "Borrow{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", bookId=" + bookId +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                '}';
    }

    public void setId(Long id) { this.id = id; }
    public void setMemberId(Long memberId) { this.memberId = memberId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }
    public void setBorrowDate(LocalDate borrowDate) { this.borrowDate = borrowDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
}
