package com.technowebtp.webapp.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String login;

    @OneToMany
    private List<Serie> series = new ArrayList<>();
}
