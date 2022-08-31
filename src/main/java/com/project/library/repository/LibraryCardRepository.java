package com.project.library.repository;

import com.project.library.model.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryCardRepository extends JpaRepository<LibraryCard, Long> {

    @Query("Select count(s.cardNumber) from LibraryCard s where s.cardNumber = ?1")
    public Integer findCardNumber(String cardNumber);
}
