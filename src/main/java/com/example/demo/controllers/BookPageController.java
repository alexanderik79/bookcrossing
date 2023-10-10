package com.example.demo.controllers;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.services.AdminService;
import com.example.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "dashboard/books.html")
@Controller
public class BookPageController {
    private final AdminService adminService;
    private final BookService bookService;
    private final BookRepository bookRepository;

    @Autowired
    public BookPageController(AdminService adminService, BookService bookService, BookRepository bookRepository) {
        this.adminService = adminService;
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    public String getAllBooks(Model model) {
        List<Book> listOfBooks = bookService.getAllBooks();
        model.addAttribute("books", listOfBooks);
        return "books";
    }

    //Антон
    @GetMapping("/newBook")
    public String showAddBookForm(Model model) {
        // Логика отображения формы для создания новой книги
        return "newbook"; // Возвращает имя представления для формы
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
        if (!adminService.getBookByID(id).isEmpty()) {
            return ResponseEntity.ok(adminService.getBookByID(id));
        } else {
            return  ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/books/{id}")
    public void deleteBookByID(@PathVariable Long id){
        adminService.deleteBookByID(id) ;
    }

    @PutMapping("/books")
    public Book update(@RequestBody Book book){
        adminService.saveOrUpdateBook(book);
        return book;
    }

}
