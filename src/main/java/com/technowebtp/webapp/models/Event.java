package com.technowebtp.webapp.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter @Setter
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate date;

    private double valeur;

    // L'utilisateur qui a cree la serie
    private String creator;

    @OneToMany
    @JoinTable(name="eventToTags")
    private List<Tag> tags = new ArrayList<>();

    private String commentaire;

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }
}
