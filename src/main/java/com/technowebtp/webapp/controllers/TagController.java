package com.technowebtp.webapp.controllers;

import com.technowebtp.webapp.models.Event;
import com.technowebtp.webapp.models.Tag;
import com.technowebtp.webapp.models.User;
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
public class TagController {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/tags")
    @ResponseBody
    public ResponseEntity createTag(@RequestBody Tag tag) {

        tagRepository.save(tag);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping(value = "/tags/{tagId}")
    @ResponseBody
    public ResponseEntity deleteTag(@PathVariable Long tagId) {

        Optional<Tag> tag = tagRepository.findById(tagId);

        tagRepository.delete(tag.get());

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping(path = "/tags/{tagId}")
    @ResponseBody
    public ResponseEntity updateTag(@PathVariable Long tagId, @RequestParam(value = "tagName", required = true) String tagName) {


        Optional<Tag> tag = tagRepository.findById(tagId);

        tag.get().setName(tagName);

        tagRepository.save(tag.get());

        return ResponseEntity.ok(HttpStatus.OK);
    }

    /**
     * get all tags
     * @return
     */
    @CachePut(value = "tags")
    @GetMapping(value = "/tags", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<Tag> getTags() {

        List<Tag> tags = tagRepository.findAll();

        return tags;
    }

    /**
     * get single tag
     * @return
     */
    @GetMapping(value = "/tags/{tagId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Tag getTag(@PathVariable Long tagId) {

        return tagRepository.findById(tagId).get();
    }

}
