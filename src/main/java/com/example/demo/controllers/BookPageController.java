package com.example.demo.controllers;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.services.BookService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookPageController {

    private final BookService bookService;
    private final UserService userService;

    private final BookRepository bookRepository;

    @Autowired
    public BookPageController(BookService bookService, UserService userService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.userService = userService;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    public String getAllBooks(Model model) {
        List<Book> listOfBooks = userService.getALLbooks();
        model.addAttribute("book", listOfBooks);
        return "books";
    }

    //Антон
    @GetMapping("/newBook")
    public String showAddBookForm(Model model) {
        // Логика отображения формы для создания новой книги
        return "newBook"; // Возвращает имя представления для формы
    }

    @ModelAttribute("newBook")
    public Book getNewBook() {
        return new Book();
    }

    //Антон
    @GetMapping("/user-books")
    public String getUserBooks(Model model) {
        // Получаем список книг из базы данных
        List<Book> userBooks = bookRepository.findAll();
        // Передаем список книг в модель
        model.addAttribute("userBooks", userBooks);

        return "user-books";
    }

    //Антон
    @PostMapping("/newBook")
    public String addBook(@ModelAttribute("newBook") Book newBook) {
        // Сохранить новую книгу в базу данных
        bookRepository.save(newBook);

        // Перенаправить пользователя на страницу, где можно увидеть добавленную книгу
        return "redirect:/user-books";
    }


    @GetMapping("/books/{id}")
    public ResponseEntity getBookByID(@PathVariable Long id) {
        if (!userService.getBookByID(id).isEmpty()) {
            return ResponseEntity.ok(userService.getBookByID(id));
        } else {
            return  ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/books/{id}")
    public void deleteBookByID(@PathVariable Long id){
        userService.deleteBookByID(id) ;
    }

    @PutMapping("/books")
    public Book update(@RequestBody Book book){
        userService.saveOrUpdateBook(book);
        return book;
    }

}
