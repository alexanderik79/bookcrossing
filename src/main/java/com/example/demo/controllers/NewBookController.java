package com.example.demo.controllers;

import com.example.demo.entity.Book;
import com.example.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NewBookController {
    BookService bookService;

    @Autowired
    public NewBookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/newbook")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "newbook";
    }
    @PostMapping("/newbook")
    public String saveUser(@ModelAttribute("book") Book book) {
        bookService.saveBook(book);
        return "redirect:/dashboard";
    }
}
