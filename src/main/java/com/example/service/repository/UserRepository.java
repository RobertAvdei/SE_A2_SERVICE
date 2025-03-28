package com.example.service.repository;

import java.util.List;

import com.example.service.model.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByName(String name);

    User findById(int id);
}

