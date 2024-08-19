package com.example.car_crud.controller;

import com.example.car_crud.exception.CarNotFoundException;
import com.example.car_crud.model.Car;
import com.example.car_crud.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        Car newCar = carService.create(car);
        return new ResponseEntity<>(newCar, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carService.getAll();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable UUID id) {
        Car car;
        try {
            car = carService.getById(id);
        } catch (CarNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @GetMapping("/model/{model}")
    public ResponseEntity<List<Car>> getCarByModel(@PathVariable String model) {
        List<Car> cars = carService.getByModel(model);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@RequestBody Car car, @PathVariable UUID id) {
        Car newCar;
        try {
            newCar = carService.update(car, id);
        } catch (CarNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newCar, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Car> patchCar(@RequestBody Car car, @PathVariable UUID id) {
        Car newCar;
        try {
            newCar = carService.patch(car, id);
        } catch (CarNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newCar, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Car> deleteCar(@PathVariable UUID id) {
        carService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
