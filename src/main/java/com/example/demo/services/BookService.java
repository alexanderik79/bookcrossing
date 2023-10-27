package com.example.demo.services;

import com.example.demo.dto.BookDTO;
import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.utils.BookDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookDTOMapper bookDTOMapper;

    @Autowired
    public BookService(BookRepository bookRepository, BookDTOMapper bookDTOMapper) {
        this.bookRepository = bookRepository;
        this.bookDTOMapper = bookDTOMapper;
    }

    public void saveBook(BookDTO book) {
        Book bookDTO = bookDTOMapper.mapToProductEntity(book);
        bookRepository.save(bookDTO);
    }

    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookDTO> listOfBooks = bookDTOMapper.mapToProductDtoList(books);
        return listOfBooks;
    }


    public List<BookDTO> findByUserId(Long userId){
        List<Book> books = bookRepository.findByUserId(userId);
        List<BookDTO> list = bookDTOMapper.mapToProductDtoList(books);
        return list;
    }


    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public void deleteBookByID(Long id) {
        bookRepository.deleteById(id);
    }

    public void saveOrUpdateBook(Book book) {
        bookRepository.save(book);
    }
}
