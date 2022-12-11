package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/publisher")
public class PublisherWeb {
    private final PublisherSQL publisherHttp;

    public PublisherWeb(PublisherSQL serviceLivro) {
        this.publisherHttp = serviceLivro;
    }

    @GetMapping
    public ResponseEntity<List<Publisher>> getPublisher() {
        return publisherHttp.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publisher> getPublisher(@PathVariable("id") int id) {
        return publisherHttp.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity createPublisher(@RequestBody Publisher publisher) {
        return publisherHttp.insert(publisher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publisher> updatePublisher(@RequestBody Publisher publisher, @PathVariable("id") int id) {
        return publisherHttp.update(publisher, id);
    }
    @DeleteMapping("/{id}")
    public void deletePublisher(@PathVariable int id) {
        publisherHttp.remove(id);
    }
}
