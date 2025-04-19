package com.technologystore.service;

import com.technologystore.model.Technology;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TechnologyService {
    private List<Technology> technologyList = new ArrayList<>();

    public void addTechnology(Technology technology) {
        technologyList.add(technology);
    }

    public List<Technology> getTechnologyList() {
        return technologyList;
    }

    public Optional<Technology> getTechnologyById(int id) {
        return technologyList.stream()
                .filter(technology -> technology.getId() == id)
                .findFirst();
    }

    public boolean updateTechnology(Technology updatedTechnology) {
        return technologyList.stream()
                .filter(technology -> technology.getId() == updatedTechnology.getId())
                .findFirst()
                .map(technology -> {
                    technology.update(updatedTechnology);
                    return true;
                })
                .orElse(false);
    }

    public boolean deleteTechnology(int id) {
        return technologyList.removeIf(technology -> technology.getId() == id);
    }
}