package com.example.demo;

import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    User findById(int id);
    ResponseEntity<List<User>> findAll() throws SQLException;
    ResponseEntity insert(User usuario);
    ResponseEntity<User> update(User livro, int id) throws SQLException;
    void remove(int id);
}
