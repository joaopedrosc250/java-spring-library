package com.example.demo;


import org.springframework.http.ResponseEntity;
import java.util.List;

public interface AuthorDAO {
    ResponseEntity<Author> findById(int id);
    ResponseEntity<List<Author>> findAll();
    ResponseEntity insert(Author author);
    ResponseEntity<Author> update(Author author, int id);
    void remove(int id);
}
