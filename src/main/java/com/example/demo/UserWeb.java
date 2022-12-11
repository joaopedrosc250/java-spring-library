package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserWeb {
    private final UserSQL userHttp;

    public UserWeb(UserSQL serviceUsuario) {
        this.userHttp = serviceUsuario;
    }
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity createUser(@RequestBody User user) {
        return userHttp.insert(user);
    }
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return userHttp.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") int id) {
        return userHttp.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") int id) {
        return userHttp.update(user, id);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userHttp.remove(id);
    }
}
