package com.example.demo.services;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Book;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(BookRepository bookRepository, UserRepository userRepository, AdminRepository adminRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
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

    public Admin getAdminByLogin (String login){
        System.out.println(login);
        return adminRepository.findAdminByLogin(login);
    }


    public void deleteBookByID(Long id){
        bookRepository.deleteById(id) ;
    }

    public void saveOrUpdateBook(Book book){
        bookRepository.save(book);
    }

}
