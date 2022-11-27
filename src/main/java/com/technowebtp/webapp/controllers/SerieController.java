package com.technowebtp.webapp.controllers;

import com.technowebtp.webapp.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SerieController {

    @PostMapping(value = "/series")
    public @ResponseBody Event createSerie(@PathVariable Integer serieId) {
        Event event = new Event();
        event.setCommentaire("This is a comment on this event");
        event.setValeur(7305);

        return event;
    }

    @GetMapping(value = "/series")
    public @ResponseBody Event getSeries(@PathVariable Integer serieId) {
        Event event = new Event();
        event.setCommentaire("This is a comment on this event");
        event.setValeur(7305);

        return event;
    }

    @GetMapping(value = "/series/{eventId}")
    public @ResponseBody Event getSerie(@PathVariable Integer serieId) {
        Event event = new Event();
        event.setCommentaire("This is a comment on this event");
        event.setValeur(7305);

        return event;
    }


    @PostMapping(value = "/series/{eventId}")
    public @ResponseBody Event updateSerie(@PathVariable Integer serieId) {
        Event event = new Event();
        event.setCommentaire("This is a comment on this event");
        event.setValeur(7305);

        return event;
    }

    @DeleteMapping(value = "/series/{eventId}")
    public @ResponseBody Event deleteSerie(@PathVariable Integer serieId) {
        Event event = new Event();
        event.setCommentaire("This is a comment on this event");
        event.setValeur(7305);

        return event;
    }
}
