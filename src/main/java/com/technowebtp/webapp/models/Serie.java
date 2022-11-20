package com.technowebtp.webapp.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    @OneToMany
    @JoinTable(name="serieToEvents")
    private List<Event> events = new ArrayList<>();
}
