package com.technowebtp.webapp.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    // L'utilisateur qui a cree la serie
    private String creator;

    //Ici on peut dire si la série, peut être partagée ou pas,
    //Elle peut avoir comme valeur
    /**
     * Ici on peut dire si la série, peut être partagée ou pas,
     * Elle peut avoir comme valeur :
     * no-access, lecture, écriture, all-access
     */
    private String access;

    @OneToMany
    @JoinTable(name="serieToEvents")
    private List<Event> events = new ArrayList<>();

    public void addEvent(Event event) {
        this.events.add(event);
    }
}
