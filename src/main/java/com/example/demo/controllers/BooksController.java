package com.example.demo.controllers;

import com.example.demo.entity.Book;

import com.example.demo.entity.User;
import com.example.demo.services.BookService;
import com.example.demo.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
public class BooksController {
    private final BookService bookService;
    private final UserService userService;

    public BooksController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }
    @GetMapping("/books")
    public String getLibrary(Model model) {
        List<Book> listOfBooks = bookService.getAllBooks();
        model.addAttribute("books", listOfBooks);
        return "books";
    }
    @GetMapping("/mybooks")
    public String myBooks(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Long userId = userService.getUserByLogin(username).getId();
        System.out.println("!!!!!!!!!!!!!!!"+userId);
        List<Book> listOfBooks = bookService.findByUserId(userId);
        model.addAttribute("books", listOfBooks);
        return "mybooks";
    }
    @GetMapping("/newbooks")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.getUserByLogin(username);
        model.addAttribute("user", user);
        return "newbooks";
    }
    @PostMapping("/newbooks")
    public String addNewBook(Book book) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Long userId = userService.getUserByLogin(username).getId();
        book.setUser(userService.getUserById(userId));
        bookService.saveBook(book);
        return "redirect:/mybooks";
    }
    @GetMapping("/owner")
    public String dashboardPage(@RequestParam("id") Long id, Model model) {
        User user = userService.getUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "owner";
        } else {
            return "redirect:/error";
        }
    }

    @GetMapping("/books/{id}")
    public ResponseEntity getBookByID(@PathVariable Long id) {
        if (bookService.getBookById(id).isPresent()) {
            return ResponseEntity.ok(bookService.getBookById(id));
        } else {
            return  ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/books/{id}")
    public void deleteBookByID(@PathVariable Long id){
        bookService.deleteBookByID(id); ;
    }

    @PutMapping("/books")
    public Book update(@RequestBody Book book){
        bookService.saveOrUpdateBook(book);
        return book;
    }
}
