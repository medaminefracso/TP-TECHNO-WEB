package com.technowebtp.webapp.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;

    private float valeur;

    @OneToMany
    @JoinTable(name="eventToTags")
    private List<Tag> tags = new ArrayList<>();

    private String commentaire;
}
