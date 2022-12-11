package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorWeb {
    private final AuthorSQL authorHttp;

    public AuthorWeb(AuthorSQL serviceLivro) {
        this.authorHttp = serviceLivro;
    }
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity createAuthor(@RequestBody Author author) {
        return authorHttp.insert(author);
    }
    @GetMapping
    public ResponseEntity<List<Author>> getAuthors() {
        return authorHttp.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable("id") int id) {
        return authorHttp.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@RequestBody Author author, @PathVariable("id") int id) {
        return authorHttp.update(author, id);
    }
    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable int id) {
        authorHttp.remove(id);
    }
}
