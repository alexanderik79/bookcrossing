package com.example.demo.controllers;

import com.example.demo.entity.Book;
import com.example.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class MyBooksController {
    private final BookService bookService;
    @Autowired
    public MyBooksController(BookService bookService) {
        this.bookService = bookService;
    }

//    @GetMapping("/books")
//    public String getAllBooks(Model model) {
//        List<Book> listOfBooks = bookService.findAllById();
//        model.addAttribute("books", listOfBooks);
//        return "books";
//    }
    @RequestMapping("/my-books")
    public String myBooks(@RequestParam("user_id") Long userId, Model model) {
        // Теперь у вас есть ID пользователя в переменной userId.
        // Вы можете использовать его в вашей логике для отображения списка книг пользователя и т.д.
        // Добавьте вашу логику здесь
        List<Book> listOfBooks = bookService.findByUserId(userId);
        model.addAttribute("books", listOfBooks);
        return "my-books"; // Замените "my-books-template" на имя вашего HTML-шаблона для списка книг пользователя
    }


}
