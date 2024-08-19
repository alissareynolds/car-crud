package com.example.car_crud.repository;

import com.example.car_crud.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {
    List<Car> findByModel(String model);
}
