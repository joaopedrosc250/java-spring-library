package com.example.demo;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderTableDAO {
    void update(OrderTable order, int id);
    void remove(int id);
    OrderTable findById(int id);
    List<OrderTable> findAll();
    ResponseEntity insert(OrderTable order);

}
