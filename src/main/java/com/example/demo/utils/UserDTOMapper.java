package com.example.demo.utils;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserDTOMapper {
    public UserDTO mapToProductDto(User entity){
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setCity(entity.getCity());
        dto.setBooks(entity.getBooks());
        dto.setRole(entity.getRole());

        return dto;
    }

    public User mapToProductEntity(UserDTO dto){
        User entity = new User();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setCity(dto.getCity());
        entity.setBooks(dto.getBooks());
        entity.setRole(dto.getRole());

        return entity;
    }
}
