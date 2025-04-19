package com.technologystore.controller;

import com.technologystore.model.*;
import com.technologystore.service.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/technology")
public class TechnologyController {

    @Autowired
    private TechnologyService technologyService;

    @PostMapping
    public Technology addTechnology(@RequestBody Technology technology) {
        technologyService.addTechnology(technology);
        return technology;
    }

    @GetMapping
    public List<Technology> getAllTechnologies() {
        return technologyService.getTechnologyList();
    }

    @GetMapping("/{id}")
    public Technology getTechnologyById(@PathVariable int id) {
        return technologyService.getTechnologyById(id)
                .orElse(null);
    }

    @PutMapping("/{id}")
    public Technology updateTechnology(@PathVariable int id, @RequestBody Technology updatedTechnology) {
        if (technologyService.updateTechnology(updatedTechnology)) {
            return updatedTechnology;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteTechnology(@PathVariable int id) {
        technologyService.deleteTechnology(id);
    }
}