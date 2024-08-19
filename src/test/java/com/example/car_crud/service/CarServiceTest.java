package com.example.car_crud.service;

import com.example.car_crud.model.Car;
import com.example.car_crud.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CarServiceTest {

    private CarService mockCarService;
    private CarRepository mockCarRepository;

    private final Car input = new Car(null, "Ford", "Ranger", "white", 1997, true, true);
    private final Car input2 = new Car(null, "Toyota", "Camry", "black", 2020, true, false);
    private final Car recordWithId = new Car(UUID.randomUUID(), "Ford", "Ranger", "white", 1997, true, true);
    private final Car recordWithId2 = new Car(recordWithId.getId(), "Toyota", "Camry", "black", 2020, true, false);

    @BeforeEach
    public void setup() {
        mockCarRepository = Mockito.mock(CarRepository.class);
        mockCarService = new CarService(mockCarRepository);
    }

}