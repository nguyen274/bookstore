package com.project.library.service.impl;

import com.project.library.model.User;
import com.project.library.repository.UserRepository;
import com.project.library.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sun.security.util.Password;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultUserService implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void register(User user) throws ExceptionInInitializerError{
        //Let's check if user already registered with us
        if(checkIfExist(user.getEmail())){
            throw new ExceptionInInitializerError("User already exists for this email");
        }
        User userEntity = new User();
        BeanUtils.copyProperties(user, userEntity);
        encodePassword(userEntity, user);
        userRepository.save(userEntity);
    }

    @Override
    public boolean checkIfExist(String email) {
        return userRepository.findByEmail(email) !=null ? true : false;
    }

    @Override
    public List<User> getAllUser() {
        return (List<User>) userRepository.getInfoStaff();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    private void encodePassword( User userEntity, User user){
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
    }
}
