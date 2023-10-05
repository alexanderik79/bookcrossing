package com.example.demo.services;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User saveUser (User user){
        System.out.println(user);
        return userRepository.save(user);
    }

    public User getUserById (long id){
        return userRepository.getOne(id);
    }

    public User getUserByLogin (String login){
        return userRepository.findByLogin(login);
    }
}