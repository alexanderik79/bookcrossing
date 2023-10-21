package com.example.demo.services;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User saveUser(User user) {
        System.out.println(user);
        Role userRole = roleRepository.findByName("USER");
        user.setRole(userRole);
        return userRepository.save(user);
    }

    public User getUserById(long id) {
        return userRepository.getOne(id);
    }

    public User getUserByLogin(String username) {
        System.out.println("UserService" + userRepository.findByUsername(username));
        return userRepository.findByUsername(username);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void deleteUserByID(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}