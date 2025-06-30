package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "home_chefs")
public class HomeChef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "specialty")
    private String specialty;

    @Column(name = "location")
    private String location;

    @Column(name = "contact_number")
    private String contactNumber;

    // Default constructor
    public HomeChef() {}

    // Parameterized constructor
    public HomeChef(String name, String specialty, String location, String contactNumber) {
        this.name = name;
        this.specialty = specialty;
        this.location = location;
        this.contactNumber = contactNumber;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getContactNumber() {
        return contactNumber;
    }
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
