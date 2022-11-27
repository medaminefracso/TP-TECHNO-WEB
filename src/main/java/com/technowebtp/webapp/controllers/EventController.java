package com.technowebtp.webapp.controllers;

import com.technowebtp.webapp.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class EventController {

    //This case is used just for testing
    @GetMapping(value = "/student/{studentId}")
    public @ResponseBody Event getTestData(@PathVariable Integer studentId) {
        Event event = new Event();
        event.setCommentaire("This is a comment on this event");
        event.setValeur(7305);

        return event;
    }

    @PostMapping(value = "/events")
    public @ResponseBody Event createEvent(@PathVariable Integer studentId) {
        Event event = new Event();
        event.setCommentaire("This is a comment on this event");
        event.setValeur(7305);

        return event;
    }

    @GetMapping(value = "/events")
    public @ResponseBody Event getEvents(@PathVariable Integer studentId) {
        Event event = new Event();
        event.setCommentaire("This is a comment on this event");
        event.setValeur(7305);

        return event;
    }

    @GetMapping(value = "/events/{eventId}")
    public @ResponseBody Event getEvent(@PathVariable Integer eventId) {
        Event event = new Event();
        event.setCommentaire("This is a comment on this event");
        event.setValeur(7305);

        return event;
    }


    @PostMapping(value = "/events/{eventId}")
    public @ResponseBody Event updateEvent(@PathVariable Integer eventId) {
        Event event = new Event();
        event.setCommentaire("This is a comment on this event");
        event.setValeur(7305);

        return event;
    }

    @DeleteMapping(value = "/events/{eventId}")
    public @ResponseBody Event deleteEvent(@PathVariable Integer eventId) {
        Event event = new Event();
        event.setCommentaire("This is a comment on this event");
        event.setValeur(7305);

        return event;
    }

}
