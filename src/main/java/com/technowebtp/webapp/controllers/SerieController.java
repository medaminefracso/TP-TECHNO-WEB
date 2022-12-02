package com.technowebtp.webapp.controllers;

import com.technowebtp.webapp.models.Event;
import com.technowebtp.webapp.models.Serie;
import com.technowebtp.webapp.models.User;
import com.technowebtp.webapp.repositories.EventRepository;
import com.technowebtp.webapp.repositories.SerieRepository;
import com.technowebtp.webapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/series")
    @ResponseBody
    public ResponseEntity createSerie(@RequestBody Serie serie, @RequestParam(value = "user", required = true) Long userId) {

        User user = userRepository.findById(userId).get();

        if(user != null) {
            serie.setCreator(user.getLogin());
        }
        serieRepository.save(serie);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping(value = "/series/{serieId}")
    @ResponseBody
    public ResponseEntity deleteSerie(@PathVariable Long serieId, @RequestParam(value = "user", required = true) Long userId) {

        Optional<Serie> serie = serieRepository.findById(serieId);

        User user = userRepository.findById(userId).get();
        if(user != null && user.getLogin().equals(serie.get().getCreator())) {

            serieRepository.delete(serie.get());
        }
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

    /**
     * get all series
     * @return
     */
    @CachePut(value = "series")
    @GetMapping(value = "/series", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<Serie> getSeries() {

        List<Serie> series = serieRepository.findAll();

        return series;
    }

    /**
     * get one serie
     * @return
     */
    @GetMapping(value = "/series/{serieId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Serie getSerie(@PathVariable Long serieId) {

        return serieRepository.findById(serieId).get();
    }

    /**
     * add event to a serie
     * @return
     */
    @PatchMapping(path = "/series/{serieId}/addEvent/{eventId}")
    @ResponseBody
    public ResponseEntity addEvent(@PathVariable Long serieId, @PathVariable Long eventId) {

        Serie serie = serieRepository.findById(serieId).get();
        Event event = eventRepository.findById(eventId).get();
        serie.addEvent(event);

        serieRepository.save(serie);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
