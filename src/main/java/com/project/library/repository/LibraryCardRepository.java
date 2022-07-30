package com.project.library.repository;

import com.project.library.model.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryCardRepository extends JpaRepository<LibraryCard, Long> {

}
