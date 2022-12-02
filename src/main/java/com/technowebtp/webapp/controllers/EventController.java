package com.technowebtp.webapp.controllers;

import com.technowebtp.webapp.models.Event;
import com.technowebtp.webapp.models.Serie;
import com.technowebtp.webapp.models.Tag;
import com.technowebtp.webapp.models.User;
import com.technowebtp.webapp.repositories.EventRepository;
import com.technowebtp.webapp.repositories.TagRepository;
import com.technowebtp.webapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class EventController {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TagRepository tagRepository;

    @PostMapping(path = "/events")
    @ResponseBody
    public ResponseEntity createEvent(@RequestBody Event event, @RequestParam(value = "user", required = true) Long userId) {

        User user = userRepository.findById(userId).get();

        if(user != null) {
            event.setCreator(user.getLogin());
        }
        eventRepository.save(event);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping(value = "/events/{eventId}")
    @ResponseBody
    public ResponseEntity deleteEvent(@PathVariable Long eventId, @RequestParam(value = "user", required = true) Long userId) {

        Optional<Event> event = eventRepository.findById(eventId);

        User user = userRepository.findById(userId).get();
        if(user != null && user.getLogin().equals(event.get().getCreator())) {

            eventRepository.delete(event.get());
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping(path = "/events/{eventId}")
    @ResponseBody
    public ResponseEntity updateEvent(@PathVariable Long eventId, @RequestParam Map<String,String> allParams) {

        LocalDate date;
        double valeur;

        Optional<Event> event = eventRepository.findById(eventId);

        if(allParams.containsKey("date")) {
            String dateStringFormat = allParams.get("title");
            date = LocalDate.parse(dateStringFormat);
            event.get().setDate(date);

        } else if(allParams.containsKey("valeur")) {
            String valueStringFormat = allParams.get("valeur");
            valeur = Long.getLong(valueStringFormat);
            event.get().setValeur(valeur);
        }

        eventRepository.save(event.get());

        return ResponseEntity.ok(HttpStatus.OK);
    }

    /**
     * get all events
     * @return
     */
    @CachePut(value = "events")
    @GetMapping(value = "/events", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<Event> getEvents() {

        List<Event> events = eventRepository.findAll();

        return events;
    }

    /**
     * get one event
     * @return
     */
    @GetMapping(value = "/events/{eventId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Event getEvent(@PathVariable Long eventId) {

        return eventRepository.findById(eventId).get();
    }

    /**
     * add event to a serie
     * @return
     */
    @PatchMapping(path = "/series/{eventId}/addTag/{tagId}")
    @ResponseBody
    public ResponseEntity addEvent(@PathVariable Long eventId, @PathVariable Long tagId) {

        Event event = eventRepository.findById(eventId).get();
        Tag tag = tagRepository.findById(tagId).get();
        event.addTag(tag);

        eventRepository.save(event);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
