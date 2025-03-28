package com.example.service.controller;
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
        origins = {"http://localhost:3000"}
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
        this.createList();
        User user = this.userRepository.findById(1);
        String query = "SELECT * FROM user";
        List<User> users = this.jdbcTemplate.query(query, new BeanPropertyRowMapper(User.class));
        return users;
    }

    @RequestMapping(
            value = {"/users/mean"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    public List<User> getUsersMean() {
        this.createList();
        User user = this.userRepository.findById(1);
        String query = "SELECT * FROM user";
        List<User> users = this.jdbcTemplate.query(query, new BeanPropertyRowMapper(User.class));
        return users;
    }

    private List<User> createList() {
        List<User> tempUsers = new ArrayList();
        User emp1 = new User();
        emp1.setName("User 1");
        User emp2 = new User();
        emp2.setName("User 2");
        tempUsers.add(emp1);
        tempUsers.add(emp2);

        assert this.userRepository != null;

        this.userRepository.saveAll(tempUsers);
        return tempUsers;
    }
}
