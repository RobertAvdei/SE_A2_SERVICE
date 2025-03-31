package com.example.service.controller;
import com.example.service.model.ReadingHabit;
import com.example.service.model.User;
import com.example.service.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
