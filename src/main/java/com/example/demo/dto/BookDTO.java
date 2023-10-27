package com.example.demo.dto;

import com.example.demo.entity.User;
import lombok.Data;

@Data
public class BookDTO {
    Long id;
    String name;
    String author;
    String genre;
    User user;
}
