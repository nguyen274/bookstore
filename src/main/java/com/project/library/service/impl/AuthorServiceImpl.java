package com.project.library.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.library.model.Author;
import com.project.library.repository.AuthorRepository;
import com.project.library.service.AuthorService;


@Service
public class AuthorServiceImpl implements AuthorService{
	
	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public List<Author> getAllAuthor() {
		return (List<Author>) authorRepository.findAll();
	}

	@Override
	public void addNew(Author author) {
		authorRepository.save(author);
		
	}

	@Override
	public Author saveAuthor(Author author) {
        authorRepository.save(author);
        return author;
	}

	@Override
	public void deleteAuthor(Long id) {
		authorRepository.deleteById(id);
		
	}

	@Override
	public Optional<Author> findAuthorById(Long id) {
		return authorRepository.findById(id);
	}

	@Override
	public List<Author> getAllBySort() {
		return authorRepository.findAllByOrderByAuthorNameAsc();
	}

}
