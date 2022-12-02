package com.technowebtp.webapp.controllers;

import com.technowebtp.webapp.models.Event;
import com.technowebtp.webapp.models.Serie;
import com.technowebtp.webapp.repositories.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class SerieController {

    @Autowired
    private SerieRepository serieRepository;

    @PostMapping(path = "/series")
    @ResponseBody
    public ResponseEntity createSerie(@RequestBody Serie serie) {

        serieRepository.save(serie);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping(value = "/series/{serieId}")
    @ResponseBody
    public ResponseEntity deleteSerie(@PathVariable Long serieId) {

        Optional<Serie> serie = serieRepository.findById(serieId);

        serieRepository.delete(serie.get());

        return ResponseEntity.ok(HttpStatus.OK);
    }


    @PatchMapping(path = "/series/{serieId}")
    @ResponseBody
    public ResponseEntity updateSerie(@PathVariable Long serieId, @RequestParam Map<String,String> allParams) {

        String title, description;

        Optional<Serie> serie = serieRepository.findById(serieId);

        if(allParams.containsKey("title")) {
            title = allParams.get("title");
            serie.get().setTitle(title);

        } else if(allParams.containsKey("description")) {
            description = allParams.get("description");
            serie.get().setDescription(description);
        }

        serieRepository.save(serie.get());

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping(value = "/series", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<Serie> getSeries() {

        List<Serie> series = serieRepository.findAll();

        return series;
    }

    @GetMapping(value = "/series/{eventId}")
    public @ResponseBody Event getSerie(@PathVariable Integer serieId) {
        Event event = new Event();
        event.setCommentaire("This is a comment on this event");
        event.setValeur(7305);

        return event;
    }

/*
    @PostMapping(value = "/series/{eventId}")
    public @ResponseBody Event updateSerie(@PathVariable Integer serieId) {
        Event event = new Event();
        event.setCommentaire("This is a comment on this event");
        event.setValeur(7305);

        return event;
    }

 */


}
