package com.project.library.service.impl;

import com.project.library.model.LibraryCard;
import com.project.library.repository.LibraryCardRepository;
import com.project.library.service.LibraryCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryCardServiceImpl implements LibraryCardService {

    @Autowired
    private LibraryCardRepository libraryCardRepository;

    @Override
    public List<LibraryCard> getAllLibraryCard() {
        return (List<LibraryCard>) libraryCardRepository.findAll();
    }

    @Override
    public void addNew(LibraryCard libraryCard) {
        libraryCardRepository.save(libraryCard);

    }

    @Override
    public LibraryCard saveLibraryCard(LibraryCard libraryCard) {
        libraryCardRepository.save(libraryCard);
        return libraryCard;
    }

    @Override
    public void deleteLibraryCard(Long id) {
        libraryCardRepository.deleteById(id);

    }

    @Override
    public Optional<LibraryCard> findLibraryCardById(Long id) {
        return libraryCardRepository.findById(id);
    }

    @Override
    public LibraryCard get(Long id) {
        return libraryCardRepository.findById(id).get();
    }

}

