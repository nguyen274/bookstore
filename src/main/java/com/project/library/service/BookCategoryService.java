package com.project.library.service;

import java.util.List;
import java.util.Optional;

import com.project.library.model.BookCategory;

public interface BookCategoryService {
    List<BookCategory> getAllBookCategory();
    
    void addNew(BookCategory bookCategory);

    BookCategory saveBookCategory(BookCategory bookCategory);

    void deleteBookCategory(Long id);

    Optional<BookCategory> findBookCategoryById(Long id);
    
    List<BookCategory> getAllBySort();

    BookCategory get(Long id);
}
