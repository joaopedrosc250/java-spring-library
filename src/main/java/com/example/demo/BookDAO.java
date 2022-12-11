package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
@Repository
public interface BookDAO {
    Book findById(int id) throws SQLException;
    ResponseEntity<List<Book>> findAll() throws SQLException;
    ResponseEntity insert(Book livro);
    ResponseEntity<Book> update(Book livro, int id) throws SQLException;
    void remove(int id);
}
