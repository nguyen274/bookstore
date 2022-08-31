package com.project.library.service;

import com.project.library.model.LibraryCard;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LibraryCardService {

        List<LibraryCard> getAllLibraryCard();

        void addNew(LibraryCard libraryCard);

        LibraryCard saveLibraryCard(LibraryCard libraryCard);

        void deleteLibraryCard(Long id);

        Optional<LibraryCard> findLibraryCardById(Long id);

         LibraryCard get(Long id);

        Integer checkUniqueCardNumber(@Param("cardNumber")String cardNumberInput);
}

