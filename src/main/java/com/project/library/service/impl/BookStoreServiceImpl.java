package com.project.library.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.library.model.BookStore;
import com.project.library.repository.BookStoreRepository;
import com.project.library.service.BookStoreService;

@Service
public class BookStoreServiceImpl implements BookStoreService{
	
	@Autowired
	private BookStoreRepository bookStoreRepository;
	
	@Override
	public List<BookStore> getAllBookStore() {
		return (List<BookStore>) bookStoreRepository.findAll();
	}

	@Override
	public void addNew(BookStore bookStore) {
		bookStoreRepository.save(bookStore);
	}

	@Override
	public BookStore saveBookStore(BookStore bookStore) {
		bookStoreRepository.save(bookStore);
		return bookStore;
	}

	@Override
	public void deleteBookStore(Long id) {
		bookStoreRepository.deleteById(id);
		
	}

	@Override
	public Optional<BookStore> findBookStoreById(Long id) {
		return bookStoreRepository.findById(id);
	}

	@Override
	public List<BookStore> getAllBySort() {
		return bookStoreRepository.findAllByOrderByBookStoreNameAsc();
	}

}
