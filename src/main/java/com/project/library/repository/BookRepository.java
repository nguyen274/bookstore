package com.project.library.repository;

import com.project.library.model.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.library.model.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
    public List<Book> findByBookCategory(BookCategory bookCategory);
    public List<Book> findByBookCategoryAndStatus(BookCategory bookCategory, Integer status);
    public Long countByStatus(Integer status);

    @Query("SELECT  b.bookCategory, COUNT(b.bookCategory)  FROM Book AS b GROUP BY b.bookCategory ")
    List<Object[]> totalBookOfCategory();

    @Query("Select count(s.bookCode) from Book s where s.bookCode = ?1")
    public Integer findISBN(String isbn);

    @Query("SELECT b FROM Book AS b where b.bookCode = ?1")
    Book getByBookCode(String bookCode);


}
