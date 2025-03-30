package com.example.service.controller;

import com.example.service.model.Book;
import com.example.service.model.ReadingHabit;
import com.example.service.model.User;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(
        origins = {"http://localhost:5173"}
)
@RestController
public class ReadingHabitController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ReadingHabitController() {}

    @RequestMapping(
            value = {"/habits"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    public List<ReadingHabit> getReadingHabits() {

        String query = "SELECT * " +
                "FROM reading_habit r LEFT JOIN book b ON r.bookid = b.bookid " +
                "LEFT JOIN user u ON r.userid = u.userid";

        return jdbcTemplate.query(
                query,
                (rs, rowNum) ->
                {
                    ReadingHabit habit =  new ReadingHabit();
                    Book book = new Book();
                    User user = new User();

                    book.setBookID(rs.getInt("bookID"));
                    book.setBookName(rs.getString("book_name"));

                    user.setUserID(rs.getInt("userID"));
                    user.setGender(rs.getString("gender"));
                    user.setAge(rs.getInt("age"));

                    habit.setBook(book);
                    habit.setUser(user);

                    habit.setHabitID(rs.getInt("habitID"));
                    habit.setPagesRead(rs.getInt("pages_read"));
                    habit.setSubmissionMoment(rs.getString("submission_moment"));

                    return habit;
                }
        );
    }

    @RequestMapping(
            value = {"/habits/totalReadPages"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    public int getTotalReadPages() {
        String query = "SELECT SUM(pages_read) FROM reading_habit";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }
}

