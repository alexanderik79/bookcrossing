package com.example.demo.dto;

import com.example.demo.entity.Book;
import com.example.demo.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    Long id;
    String username;
    String name;
    String email;
    String phone;
    String city;
    List<Book> books;
    Role role;
}
