package com.example.service.repository;

import java.util.List;

import com.example.service.model.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByGender(String gender);

    User findByUserID(int userID);
}

