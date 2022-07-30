package com.project.library.service;

import java.util.List;
import java.util.Optional;

import com.project.library.model.BookCategory;
import org.springframework.stereotype.Repository;

import com.project.library.model.Book;

@Repository
public interface BookService {
    List<Book> getAllBook();
    
    void addNew(Book book);

    Book saveBook(Book book);

    void deleteBook(Long id);

    Optional<Book> findBookById(Long id);

    List<Book> getByBookCategory(BookCategory bookCategory);

    List<Book> getAvailableByCategory(BookCategory category);

    List<Book> get(List<Long> bookIds);

    List<Object[]> totalBookOfCategory();
}
