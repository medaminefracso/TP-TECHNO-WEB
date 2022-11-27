package com.technowebtp.webapp.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Getter @Setter
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;

    private double valeur;

    @OneToMany
    @JoinTable(name="eventToTags")
    private List<Tag> tags = new ArrayList<>();

    private String commentaire;
}
