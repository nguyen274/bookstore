package com.project.library.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.library.model.BookCategory;
import com.project.library.repository.BookCategoryRepository;
import com.project.library.service.BookCategoryService;

@Service
public class BookCategoryServiceImpl implements BookCategoryService{
	
	@Autowired
	private BookCategoryRepository bookCategoryRepository;
	
	@Override
	public List<BookCategory> getAllBookCategory() {
		return (List<BookCategory>) bookCategoryRepository.findAll();
	}

	@Override
	public BookCategory saveBookCategory(BookCategory bookCategory) {
		bookCategoryRepository.save(bookCategory);
		return bookCategory;
		
	}

	@Override
	public void deleteBookCategory(Long id) {
		bookCategoryRepository.deleteById(id);
		
	}

	@Override
	public Optional<BookCategory> findBookCategoryById(Long id) {
		return bookCategoryRepository.findById(id);
	}

	@Override
	public void addNew(BookCategory bookCategory) {
		bookCategoryRepository.save(bookCategory);
		
	}

	@Override
	public List<BookCategory> getAllBySort() {
		return bookCategoryRepository.findAllByOrderByBookCategoryNameAsc();
	}

	@Override
	public BookCategory get(Long id) {
		return bookCategoryRepository.findById(id).get();
	}

}
