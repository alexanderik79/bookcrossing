package com.example.demo.controllers;

import com.example.demo.entity.Book;
import com.example.demo.entity.User;
import com.example.demo.services.BookService;
import com.example.demo.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminPageController {
    private final BookService bookService;
    private final UserService userService;

    public AdminPageController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping("/adminpage")
    public String getAdminPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.getUserByLogin(username);
        model.addAttribute("user", user);
        return "adminpage";
    }


    @PutMapping("/adminpage/update/books")
    public String updateBook(@RequestBody Book book) {
        bookService.saveOrUpdateBook(book);
        return "books";
    }

    @PutMapping("/adminpage/update/users")
    public String updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return "users";
    }

    @PostMapping("/adminpage/delete/users/")
    public String deleteUserByID(@RequestParam Long id) {
        userService.deleteUserByID(id);
        return "redirect:/adminpage/users";
    }

    @PostMapping("/adminpage/delete/books{id}")
    public String deleteBookByID(@PathVariable Long id) {
        bookService.deleteBookByID(id);
        return "redirect:/adminpage/books";
    }

    @GetMapping("/adminpage/books")
    public String getLibraryAdmin(Model model) {
        List<Book> listOfBooks = bookService.getAllBooks();
        model.addAttribute("books", listOfBooks);
        return "books";
    }

    @GetMapping("/adminpage/users")
    public String getUsers(Model model) {
        List<User> listOfUsers = userService.getAllUsers();
        model.addAttribute("users", listOfUsers);
        return "users";
    }

}
