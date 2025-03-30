package com.example.service.controller;

import com.example.service.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(
        origins = {"http://localhost:5173"}
)
@RestController
public class BookController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public BookController() {}

    @RequestMapping(
            value = {"/books"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    public List<Book> getBooks() {
        String query = "SELECT * FROM book";
        List<Book> books = this.jdbcTemplate.query(query, new BeanPropertyRowMapper(Book.class));
        return books;
    }
}
