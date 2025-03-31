package com.example.service.controller;

import com.example.service.dto.CreateUserDto;
import com.example.service.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(
            value = {"/books/readerCount/{id}"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    public int getBookReaderCount(@PathVariable("id") int bookID) {
        String query = String.format("select count(case bookid when %d then 1 else null end) as ReaderCount\n" +
                "    from reading_habit", bookID) ;
        int readers = this.jdbcTemplate.queryForObject(query, Integer.class);
        return readers;
    }

    @PostMapping(
            value = {"/books/{id}"},
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    public void updateBook(@RequestBody Book book, @PathVariable("id") int bookID) {
        String query = "UPDATE book SET book_name= ? " +
                "WHERE bookid= ?";
        this.jdbcTemplate.update(query, book.getBookName(), bookID);
    }
}
