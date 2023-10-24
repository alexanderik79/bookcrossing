package com.example.demo.services;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.UserDTOMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final UserDTOMapper userDTOMapper;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, UserDTOMapper userDTOMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userDTOMapper = userDTOMapper;
    }
    public User saveUser (User user){
        System.out.println(user);
        Role userRole = roleRepository.findByName("USER");
        user.setRole(userRole);
        return userRepository.save(user);
    }

    public User getUserById (long id){
        return userRepository.getOne(id);
    }

//    public User getUserByLogin (String username){
//        System.out.println("UserService"+userRepository.findByUsername(username));
//        return userRepository.findByUsername(username);
//    }

    public UserDTO getUserByLogin(String username){
        return userDTOMapper.mapToProductDto(userRepository.findByUsername(username));
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream() //создали из листа стрим
                .map(userDTOMapper::mapToProductDto) //оператором из streamAPI map, использовали для каждого элемента метод mapToProductDto из класса MappingUtils
                .collect(Collectors.toList()); //превратили стрим обратно в коллекцию, а точнее в лист
    }

    public boolean usernameExists(String username) {
        User user = userRepository.findByUsername(username);
        return user != null;
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