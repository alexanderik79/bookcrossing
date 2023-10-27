package com.example.demo.utils;

import com.example.demo.dto.BookDTO;
import com.example.demo.entity.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookDTOMapper {
    public BookDTO mapToProductDto(Book entity){
        BookDTO dto = new BookDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAuthor(entity.getAuthor());
        dto.setGenre(entity.getGenre());
        dto.setUser(entity.getUser());
        return dto;
    }

    public List<BookDTO> mapToProductDtoList(List<Book> entities) {
        List<BookDTO> dtos = new ArrayList<>();
        for (Book entity : entities) {
            dtos.add(mapToProductDto(entity));
        }
        return dtos;
    }

    public Book mapToProductEntity(BookDTO dto){
        Book entity = new Book();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setAuthor(dto.getAuthor());
        entity.setGenre(dto.getGenre());
        entity.setUser(dto.getUser());

        return entity;
    }
}
