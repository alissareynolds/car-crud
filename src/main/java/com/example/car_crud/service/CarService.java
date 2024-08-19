package com.example.car_crud.service;

import com.example.car_crud.exception.CarNotFoundException;
import com.example.car_crud.model.Car;
import com.example.car_crud.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car create(Car car) {
        Car newCar = new Car(car.getModel(), car.getMake(), car.getColor(), car.getYear(), car.getHasAirConditioning(), car.getIsTruck());
        return carRepository.save(newCar);
    }

    public List<Car> getAll() {
        return carRepository.findAll();
    }

    public Car getById(UUID id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isEmpty()) {
            throw new CarNotFoundException("A car with id: " + id + " was not found.");
        }
        return optionalCar.get();
    }

    public Car getByModel(String model) {
        Optional<Car> optionalCar = carRepository.findByModel(model);
        if (optionalCar.isEmpty()) {
            throw new CarNotFoundException("A car with model: " + model + " was not found.");
        }
        return optionalCar.get();
    }

    public Car update(Car car, UUID id) {
        Optional<Car> originalCar = carRepository.findById(id);
        if (originalCar.isEmpty()) {
            throw new CarNotFoundException("A car with id: " + id + " was not found.");
        }
        Car updatedCar = new Car(id, car.getModel(), car.getMake(), car.getColor(), car.getYear(), car.getHasAirConditioning(), car.getIsTruck());
        return carRepository.save(updatedCar);
     }

    public Car patch(Car car, UUID id) {
        Optional<Car> originalCar = carRepository.findById(id);
        if (originalCar.isEmpty()) {
            throw new CarNotFoundException("A car with id: " + id + " was not found.");
        }
        Car updatedCar = originalCar.get();
        if (car.getModel() != null) {
            updatedCar.setModel(car.getModel());
        }
        if (car.getMake() != null) {
            updatedCar.setMake(car.getMake());
        }
        if (car.getColor() != null) {
            updatedCar.setColor(car.getColor());
        }
        if (car.getYear() != null) {
            updatedCar.setYear(car.getYear());
        }
        if (car.getHasAirConditioning() != null) {
            updatedCar.setHasAirConditioning(car.getHasAirConditioning());
        }
        if (car.getIsTruck() != null) {
            updatedCar.setIsTruck(car.getIsTruck());
        }
        return carRepository.save(updatedCar);
    }

    public Car delete(UUID id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isEmpty()) {
            throw new CarNotFoundException("A car with id: " + id + " was not found.");
        }
        carRepository.delete(optionalCar.get());
        return optionalCar.get();
    }
}
