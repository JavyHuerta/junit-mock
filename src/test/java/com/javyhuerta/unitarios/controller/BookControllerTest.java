package com.javyhuerta.unitarios.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javyhuerta.unitarios.entity.Book;
import com.javyhuerta.unitarios.repository.BookRepository;
import com.javyhuerta.unitarios.service.BookService;
import com.javyhuerta.unitarios.service.BookServiceImpl;
import org.h2.command.dml.MergeUsing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    // Dependencias, se simulan
    @MockBean
    BookService bookService;


    @Test
    void findAll() throws Exception {
        List<Book> books = List.of(
                new Book(1L,"Book 1", "Description"),
                new Book(2L,"Book 2", "Description"),
                new Book(3L,"Book 3", "Description")
        );
        when(bookService.findAll()).thenReturn(books);
         mockMvc.perform(
                 get("/api/books").contentType(MediaType.APPLICATION_JSON)
         ).andExpect(status().isOk())
                 .andExpect( jsonPath("$", hasSize(3)))
                 .andExpect( jsonPath("$[1].title", is("Book 2")));


    }

    @Test
    void findById() {
    }

    @Test
    void save() throws Exception {
        Book book = new Book(null,"Book 5","Description 5");
        Book bookDb = new Book(5L,"Book 5","Description 5");

        when(bookService.save(any())).thenReturn(bookDb);

        mockMvc.perform(
                        post("/api/books").contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(this.mapper.writeValueAsString(book))
                )
                .andExpect(status().isOk())
                .andExpect( jsonPath("$", notNullValue()))
                .andExpect( jsonPath("$.id", is(5)))
                .andExpect( jsonPath("$.title",is("Book 5")));


    }
}