package com.javyhuerta.unitarios.service;

import com.javyhuerta.unitarios.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);

    Book save(Book book);
}
