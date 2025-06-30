package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.HomeChef;
import com.example.demo.repository.HomeChefRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chefs")
public class HomeChefController {

    @Autowired
    private HomeChefRepository homechefsRepository;

    // ✅ Get all chefs
    @GetMapping
    public List<HomeChef> getAllHomeChefs() {
        return homechefsRepository.findAll();
    }

    // ✅ Get chef by ID
    @GetMapping("/homechefs/{id}")
    public ResponseEntity<HomeChef> getHomeChefById(@PathVariable("id") long id) {
        Optional<HomeChef> homeChefData = homechefsRepository.findById(id);
        return homeChefData.map(chef -> new ResponseEntity<>(chef, HttpStatus.OK))
                           .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // ✅ Create new chef
    @PostMapping
    public HomeChef createHomeChef(@RequestBody HomeChef homeChef) {
        return homechefsRepository.save(homeChef);
    }

    // ✅ Update chef
    @PutMapping("/{id}")
    public HomeChef updateHomeChef(@PathVariable Long id, @RequestBody HomeChef updatedHomeChef) {
        return homechefsRepository.findById(id).map(homeChef -> {
            homeChef.setId(updatedHomeChef.getId());
            homeChef.setContactNumber(updatedHomeChef.getContactNumber());
            homeChef.setLocation(updatedHomeChef.getLocation());
            homeChef.setName(updatedHomeChef.getName());
            homeChef.setSpecialty(updatedHomeChef.getSpecialty());
            return homechefsRepository.save(homeChef);
        }).orElseGet(() -> {
            updatedHomeChef.setId(id);
            return homechefsRepository.save(updatedHomeChef);
        });
    }

    // ✅ Delete chef
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteHomeChef(@PathVariable Long id) {
        try {
            homechefsRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ✅ Optional: Get chefs by location
    @GetMapping("/location/{location}")
    public List<HomeChef> getChefsByLocation(@PathVariable String location) {
        return homechefsRepository.findByLocation(location);
    }

    // ✅ Optional: Search chefs by name (case-insensitive)
    @GetMapping("/search")
    public List<HomeChef> searchByName(@RequestParam("name") String name) {
        return homechefsRepository.findByNameContainingIgnoreCase(name);
    }
}
