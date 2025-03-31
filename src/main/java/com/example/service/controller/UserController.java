package com.example.service.controller;
import com.example.service.dto.CreateUserDto;
import com.example.service.model.Book;
import com.example.service.model.ReadingHabit;
import com.example.service.model.User;
import com.example.service.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(
        origins = {"http://localhost:5173"}
)
@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    private List<User> users;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserController() {
    }

    @RequestMapping(
            value = {"/users"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    public List<User> getUsers() {
        String query = "SELECT * FROM user";
        List<User> users = this.jdbcTemplate.query(query, new BeanPropertyRowMapper(User.class));
        return users;
    }

    @PostMapping(
            value = {"/users"},
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    public void createUser(@RequestBody CreateUserDto user) {
        String query = "insert into user (age, gender)" +
                "values (?, ?)";
        this.jdbcTemplate.update(query, Integer.parseInt(user.getAge()), user.getGender());
    }

    @RequestMapping(
            value = {"/users/mean"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    public int getUsersMean() {
        String query = "SELECT AVG(age) FROM user";
        return this.jdbcTemplate.queryForObject(query, Integer.class);
    }

    @RequestMapping(
            value = {"/users/hasName"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    public boolean gethasName() {
        String query = "SELECT COUNT(*) AS CNTREC FROM pragma_table_info('user') WHERE name='name'";
        int result = this.jdbcTemplate.queryForObject(query, Integer.class);
        return result > 0;
    }

    @RequestMapping(
            value = {"/users/switchName"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    public void switchName() {
        boolean hasName = this.gethasName();
        String query;
        if (hasName) {
            query = "create table user_dg_tmp\n" +
                    "(\n" +
                    "    userID integer not null\n" +
                    "        constraint user_pk\n" +
                    "            primary key autoincrement,\n" +
                    "    age    integer not null,\n" +
                    "    gender varchar(255)\n" +
                    ");\n" +
                    "\n" +
                    "insert into user_dg_tmp(userID, age, gender)\n" +
                    "select userID, age, gender\n" +
                    "from user;\n" +
                    "\n" +
                    "drop table user;\n" +
                    "\n" +
                    "alter table user_dg_tmp\n" +
                    "    rename to user;";
        }else {
            query = "ALTER TABLE user ADD name varchar(255);";
        }
        System.out.println(hasName);
        System.out.println(query);
        this.jdbcTemplate.update(query);

    }



    @RequestMapping(
            value = {"/users/getMultiReaders"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    public int getMultiReaders() {
        String sql = "Select sum(count) \n" +
                "  from (select userid, \n" +
                "               count(DISTINCT userid) as Count \n" +
                "          from reading_habit\n" +
                "         group by userid\n" +
                "        having count(userid) >= 2);";
        return this.jdbcTemplate.queryForObject(sql, Integer.class);

    }

    @RequestMapping(
            value = {"/users/habit/{id}"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    public List<ReadingHabit> getUserHabit(@PathVariable("id") int userID) {
        String query = String.format("SELECT * " +
                "FROM reading_habit r LEFT JOIN book b ON r.bookid = b.bookid " +
                "LEFT JOIN user u ON r.userid = u.userid " +
                "where u.userID = %d ",userID);

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

}
