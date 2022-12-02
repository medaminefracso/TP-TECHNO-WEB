package com.technowebtp.webapp.controllers;

import com.technowebtp.webapp.models.Event;
import com.technowebtp.webapp.models.Serie;
import com.technowebtp.webapp.models.User;
import com.technowebtp.webapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping(path = "/users")
    @ResponseBody
    public ResponseEntity createUser(@RequestBody User user) {
        System.out.println("The client wants to create the user " + user.getId() + " " + user.getLogin());

        userRepository.save(user);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping(value = "/users/{userId}")
    @ResponseBody
    public ResponseEntity deleteUser(@PathVariable Long userId) {

        Optional<User> user = userRepository.findById(userId);

        userRepository.delete(user.get());

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping(path = "/users/{userId}")
    @ResponseBody
    public ResponseEntity updateSerie(@PathVariable Long userId, @RequestParam Map<String,String> allParams) {

        String login, role;

        Optional<User> user = userRepository.findById(userId);

        if(allParams.containsKey("title")) {
            login = allParams.get("title");
            user.get().setLogin(login);

        } else if(allParams.containsKey("description")) {
            role = allParams.get("description");
            user.get().setRole(role);
        }

        userRepository.save(user.get());

        return ResponseEntity.ok(HttpStatus.OK);
    }

}
