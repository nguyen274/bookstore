package com.project.library.service;

import com.project.library.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void register(final User user) throws ExceptionInInitializerError;

    boolean checkIfExist(final String email);

    List<User> getAllUser();

    void save(User user);

    Optional<User> findUserById(Long id);
}
