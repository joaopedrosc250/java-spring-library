package com.example.demo;

import com.example.demo.Publisher;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import java.util.List;

public interface PublisherDAO {
    ResponseEntity insert(Publisher publisher);

    ResponseEntity<Publisher> findById(int id);

    ResponseEntity<List<Publisher>> findAll() throws SQLException;

    ResponseEntity<Publisher> update(Publisher publisher, int id) throws SQLException;

    void remove(int id);
}
