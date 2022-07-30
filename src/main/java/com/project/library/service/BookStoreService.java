package com.project.library.service;

import java.util.List;
import java.util.Optional;

import com.project.library.model.BookStore;

public interface BookStoreService {
    List<BookStore> getAllBookStore();
    
    void addNew(BookStore bookStore);

    BookStore saveBookStore(BookStore bookStore);

    void deleteBookStore(Long id);

    Optional<BookStore> findBookStoreById(Long id);
    
    List<BookStore> getAllBySort();
}
