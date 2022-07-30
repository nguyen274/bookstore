package com.project.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.library.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{
	public List<Author> findAllByOrderByAuthorNameAsc();
}
