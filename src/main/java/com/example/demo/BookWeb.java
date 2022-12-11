package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookWeb {
    private final BookSQL bookHttp;

    public BookWeb(BookSQL serviceLivro) {
        this.bookHttp = serviceLivro;
    }


    @GetMapping
    public ResponseEntity<List<Book>> getLivros() {
        return bookHttp.findAll();
    }

    @GetMapping("/{id}")
    public Book getLivro(@PathVariable("id") int id) {
        return bookHttp.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity createLivro(@RequestBody Book livro) {
        return bookHttp.insert(livro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateLivro(@RequestBody Book livro, @PathVariable("id") int id) {
        return bookHttp.update(livro, id);
    }

    @DeleteMapping("/{id}")
    public void deleteLivro(@PathVariable int id) {
        bookHttp.remove(id);
    }
}