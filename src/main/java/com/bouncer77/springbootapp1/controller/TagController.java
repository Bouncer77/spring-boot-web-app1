package com.bouncer77.springbootapp1.controller;

import com.bouncer77.springbootapp1.entity.Tag;
import com.bouncer77.springbootapp1.form.TagForm;
import com.bouncer77.springbootapp1.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 09.09.2020
 */

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    TagService tagService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TagForm tagForm) {

        Tag tag = new Tag(tagForm.getName(), tagForm.getDescription());
        tagService.create(tag);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Tag>> read() {

        final List<Tag> authors = tagService.readAll();

        return authors != null && !authors.isEmpty()
                ? new ResponseEntity<>(authors, HttpStatus.OK) // 200 OK
                : new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Tag> read(@PathVariable(name = "id") long id) {

        final Tag tag = tagService.read(id);

        return tag != null
                ? new ResponseEntity<>(tag, HttpStatus.OK) // 200 OK
                : new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") long id, @RequestBody TagForm tagForm) {

        final boolean updated = tagService.update(id, tagForm);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {

        final boolean deleted = tagService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
