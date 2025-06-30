package com.example.demo.repository;

import com.example.demo.model.HomeChef;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HomeChefRepository extends JpaRepository<HomeChef, Long> {
    List<HomeChef> findByLocation(String location);
    List<HomeChef> findByNameContainingIgnoreCase(String name);
    boolean existsByContactNumber(String contactNumber);
}
