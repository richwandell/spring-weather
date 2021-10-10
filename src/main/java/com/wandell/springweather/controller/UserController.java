package com.wandell.springweather.controller;

import com.wandell.springweather.data.User;
import com.wandell.springweather.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/api/users")
    public List<User> listAll(Model model) {
        return repo.findAll();
    }

    @PostMapping("/api/users")
    public User saveUser(@RequestBody User newUser) {
        return repo.save(newUser);
    }

}