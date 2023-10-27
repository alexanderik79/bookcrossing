package com.example.demo.controllers;

import com.example.demo.dto.BookDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Book;

import com.example.demo.entity.User;
import com.example.demo.services.BookService;
import com.example.demo.services.UserService;
import com.example.demo.utils.UserDTOMapper;
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
    private final UserDTOMapper userDTOMapper;

    public BooksController(BookService bookService, UserService userService, UserDTOMapper userDTOMapper) {
        this.bookService = bookService;
        this.userService = userService;
        this.userDTOMapper = userDTOMapper;
    }


    @GetMapping("/books")
    public String getLibrary(Model model) {
        List<BookDTO> listOfBooks = bookService.getAllBooks();
        model.addAttribute("books", listOfBooks);
        return "books";
    }

    @GetMapping("/mybooks")
    public String myBooks(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserDTO user = userService.getUserByLogin(username);
        List<BookDTO> listOfBooks = bookService.findByUserId(user.getId());
        model.addAttribute("books", listOfBooks);
        return "mybooks";
    }

    @GetMapping("/newbooks")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserDTO user = userService.getUserByLogin(username);
        model.addAttribute("user", user);
        return "newbooks";
    }


    @PostMapping("/newbooks")
    public String addNewBook(BookDTO book) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserDTO userDTO = userService.getUserByLogin(username);
        User user = userDTOMapper.mapToProductEntity(userDTO);
        book.setUser(user);
        bookService.saveBook(book);
        return "redirect:/mybooks";
    }

    @GetMapping("/owner")
    public String dashboardPage(@RequestParam("id") Long id, Model model) {
        UserDTO user = userService.getUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "owner";
        } else {
            return "redirect:/error";
        }
    }
}
