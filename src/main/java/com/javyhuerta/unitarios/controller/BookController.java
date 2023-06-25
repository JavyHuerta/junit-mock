package com.javyhuerta.unitarios.controller;

import com.javyhuerta.unitarios.entity.Book;
import com.javyhuerta.unitarios.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api")
public class BookController {

    private BookService bookService;
    public BookController(BookService bookService){
        this.bookService =bookService;
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> findAll(){
        return ResponseEntity.ok(bookService.findAll());
    }

    @PostMapping("/books")
    public ResponseEntity<Book> save(@RequestBody Book book){
        return ResponseEntity.ok(bookService.save(book));
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id){
        Book book = bookService.findById(id).orElseThrow();
        return ResponseEntity.ok(book);
    }
}
