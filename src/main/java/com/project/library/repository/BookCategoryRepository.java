package com.project.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.project.library.model.BookCategory;

@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory, Long>{
	public List<BookCategory> findAllByOrderByBookCategoryNameAsc();
}
