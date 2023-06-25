package com.javyhuerta.unitarios.service;

import com.javyhuerta.unitarios.entity.Book;
import com.javyhuerta.unitarios.repository.BookRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceImplTest {

    // Lo que se va a testear
    BookService bookService;

    // Dependencias, se simulan
    @Mock
    BookRepository bookRepository;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
        bookService = new BookServiceImpl(bookRepository);
    }

    @Test
    void findAll() {
        // Configurar Mock
        when(bookRepository.findAll()).thenReturn(List.of());

        // Ejecutar el comportamiento a teestear
        List<Book> books = bookService.findAll();

        // Comprobaciones

        // Verificaciones de JUnit
        assertNotNull(books);
        assertEquals(0, books.size());

        // Verificaciones de Mockito
        verify(bookRepository, times(1)).findAll();

    }

    @Test
    void findByIdFound() {
        // Configurar Mock
        when(bookRepository.findById(any())).thenReturn(Optional.of(new Book(1L,"book 1", "descripcion")));

        // Ejecutar el comportamiento a teestear
        Optional<Book> book = bookService.findById(25L);

        // Comprobaciones

        // Verificaciones de JUnit
        assertNotNull(book);
        assertTrue(book.isPresent());
        assertEquals("book 1", book.get().getTitle());

        // Verificaciones de Mockito
        verify(bookRepository, times(1)).findById(25L);
    }

    @Test
    void findByIdNotFound() {
    }

    @Test
    void findByIdWrong() {
    }
}