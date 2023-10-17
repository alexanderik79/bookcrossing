package com.example.demo.services;

import com.example.demo.entity.Book;
import com.example.demo.entity.User;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final BookRepository bookRepository;

    @Autowired
    public UserService(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }
    public void saveUser(User user){
        userRepository.save(user);
    }

    public Optional<User> getUserById (Long id){
        return userRepository.findById(id);
    }

    public User getUserByLogin (String login){
        return userRepository.findByLogin(login);
    }

    public void saveAll(List<Book> books) {
        bookRepository.saveAll(books);

    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    public List<Book> getALLbooks(){
        return bookRepository.findAll();
    }

    public Optional<Book> getBookByID(Long id) {
        return bookRepository.findById(id);
    }




    public void deleteBookByID(Long id){
        bookRepository.deleteById(id) ;
    }

    public void saveOrUpdateBook(Book book){
        bookRepository.save(book);
    }



}
