package com.javyhuerta.unitarios.repository;

import com.javyhuerta.unitarios.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    BookRepository repository;


    private Book insert(){
        Book book = new Book(null,"Libro 1","Descripcion");
        Book bookDb = entityManager.persist(book);
        entityManager.flush();
        return  bookDb;
    }

    @Test
    void findAll() {
        Book book = insert();
        List<Book> books = repository.findAll();
        assertEquals(1,books.size());
        assertEquals(book.getId(), books.get(0).getId());
    }

    @Test
    @Sql("book.sql")
    void findAllWihtSql() {
        List<Book> books = repository.findAll();
        assertEquals(2,books.size());
    }

    @Test
    @SqlGroup({
            @Sql("book1.sql"),
            @Sql("book2.sql"),
            @Sql("book3.sql")
    })
    void findAllWihtSqlGroup() {
        List<Book> books = repository.findAll();
        assertEquals(2,books.size());
    }
}