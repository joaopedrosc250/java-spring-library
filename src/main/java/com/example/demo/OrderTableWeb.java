package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/order")
public class OrderTableWeb {
    private final OrderTableSQL orderHttp;

    public OrderTableWeb(OrderTableSQL serviceOrder) {
        this.orderHttp = serviceOrder;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity createOrder(@RequestBody OrderTable order) {
        return orderHttp.insert(order);
    }

    @GetMapping
    public List<OrderTable> getOrders() {
        return orderHttp.findAll();
    }

    @GetMapping("/{id}")
    public OrderTable getOrder(@PathVariable("id") int id) {
        return orderHttp.findById(id);
    }
    @PutMapping("/{id}")
    public void updateOrder(@RequestBody OrderTable order, @PathVariable("id") int id) {
        orderHttp.update(order, id);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable int id) {
        orderHttp.remove(id);
    }
}
