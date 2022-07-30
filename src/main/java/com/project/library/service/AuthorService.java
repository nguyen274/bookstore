package com.project.library.service;

import java.util.List;
import java.util.Optional;

import com.project.library.model.Author;


public interface AuthorService {
	
    List<Author> getAllAuthor();

    void  addNew(Author author);

    Author saveAuthor(Author author);

    void deleteAuthor(Long id);

    Optional<Author> findAuthorById(Long id);
    
    List<Author> getAllBySort();
    
}
