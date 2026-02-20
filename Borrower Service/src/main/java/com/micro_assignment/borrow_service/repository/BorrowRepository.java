package com.micro_assignment.borrow_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro_assignment.borrow_service.model.Borrow;

import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {

    List<Borrow> findByMemberId(Long memberId);

    List<Borrow> findByBookId(Long bookId);
}
