package com.example.secondbook.controllers;

import com.example.secondbook.models.Book;
import com.example.secondbook.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {

    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/get-books")
    public String getBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "book-list";
    }

    @GetMapping("/book-create")
    public String createNewBookForm(Book book) {
        return "book-create";
    }

    @PostMapping("/book-create")
    public String createNewBook(Book book) {
        bookRepository.save(book);
        return "redirect:/get-books";
    }

    @GetMapping("/book-delete/{id}")
    public String deleteBook(@PathVariable("id") int bookId){
        bookRepository.deleteById(bookId);
        return "redirect:/get-books";
    }

    @GetMapping("/book-update/{id}")
    public String updateBook(@PathVariable("id") int id,Model model){
        model.addAttribute("book",bookRepository.findById(id));
        return "book-update";
    }

    @PostMapping("/book-update")
    public String updateBook(Book book){
        bookRepository.save(book);
        return "redirect:/get-books";
    }
}
