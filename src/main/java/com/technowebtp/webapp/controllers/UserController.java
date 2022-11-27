package com.technowebtp.webapp.controllers;

import com.technowebtp.webapp.models.Event;
import com.technowebtp.webapp.models.User;
import com.technowebtp.webapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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


}
