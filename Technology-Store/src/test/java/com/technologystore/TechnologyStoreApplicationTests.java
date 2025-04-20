package com.technologystore;

import com.technologystore.model.Manufacturer;
import com.technologystore.model.Technology;
import com.technologystore.service.TechnologyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TechnologyServiceTest {

    private TechnologyService technologyService;

    @BeforeEach
    public void setUp() {
        technologyService = new TechnologyService();
    }

    @Test
    public void testAddTechnology() {
        Manufacturer manufacturer = new Manufacturer("Manufacturer1", "Location1");
        Technology technology = new Technology("Tech1", 1, manufacturer);

        technologyService.addTechnology(technology);

        List<Technology> technologies = technologyService.getTechnologyList();
        assertEquals(1, technologies.size());
        assertEquals("Tech1", technologies.getFirst().getName());
        assertEquals(1, technologies.getFirst().getId());
        assertEquals("Manufacturer1", technologies.getFirst().getManufacturer().getName());
    }

    @Test
    public void testGetTechnologyById() {
        Manufacturer manufacturer = new Manufacturer("Manufacturer1", "Location1");
        Technology technology = new Technology("Tech1", 1, manufacturer);
        technologyService.addTechnology(technology);

        Optional<Technology> foundTechnology = technologyService.getTechnologyById(1);
        assertTrue(foundTechnology.isPresent());
        assertEquals("Tech1", foundTechnology.get().getName());
        assertEquals(1, foundTechnology.get().getId());

        Optional<Technology> notFoundTechnology = technologyService.getTechnologyById(2);
        assertFalse(notFoundTechnology.isPresent());
    }

    @Test
    public void testUpdateTechnology() {
        Manufacturer manufacturer = new Manufacturer("Manufacturer1", "Location1");
        Technology technology = new Technology("Tech1", 1, manufacturer);
        technologyService.addTechnology(technology);

        Manufacturer updatedManufacturer = new Manufacturer("UpdatedManufacturer", "UpdatedLocation");
        Technology updatedTechnology = new Technology("UpdatedTech", 1, updatedManufacturer);

        boolean isUpdated = technologyService.updateTechnology(updatedTechnology);
        assertTrue(isUpdated);

        Optional<Technology> foundTechnology = technologyService.getTechnologyById(1);
        assertTrue(foundTechnology.isPresent());
        assertEquals("UpdatedTech", foundTechnology.get().getName());
        assertEquals("UpdatedManufacturer", foundTechnology.get().getManufacturer().getName());
    }

    @Test
    public void testDeleteTechnology() {
        Manufacturer manufacturer = new Manufacturer("Manufacturer1", "Location1");
        Technology technology = new Technology("Tech1", 1, manufacturer);
        technologyService.addTechnology(technology);

        boolean isDeleted = technologyService.deleteTechnology(1);
        assertTrue(isDeleted);

        Optional<Technology> foundTechnology = technologyService.getTechnologyById(1);
        assertFalse(foundTechnology.isPresent());
    }
}