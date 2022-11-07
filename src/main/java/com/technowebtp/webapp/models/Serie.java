package com.technowebtp.webapp.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String titre;

    private String description;

    /*
    @OneToMany
    List<Event> events;
    */
}
