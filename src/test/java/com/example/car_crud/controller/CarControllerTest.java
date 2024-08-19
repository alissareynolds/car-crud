package com.example.car_crud.controller;

import com.example.car_crud.exception.CarNotFoundException;
import com.example.car_crud.model.Car;
import com.example.car_crud.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CarControllerTest {

    private CarController carController;
    private CarService mockCarService;

    private final Car input = new Car(null, "Ford", "Ranger", "white", 1997, true, true);
    private final Car input2 = new Car(null, "Toyota", "Camry", "black", 2020, true, false);
    private final Car recordWithId = new Car(UUID.randomUUID(), "Ford", "Ranger", "white", 1997, true, true);
    private final Car recordWithId2 = new Car(recordWithId.getId(), "Toyota", "Camry", "black", 2020, true, false);

    public final UUID id = UUID.fromString("59c47568-fde0-4dd7-9aef-03db6a962810");

    @BeforeEach
    public void setup() {
        mockCarService = Mockito.mock(CarService.class);
        carController = new CarController(mockCarService);
    }

    @Test
    public void createCar_shouldReturnCarAndCREATEDHttpStatus() {
        Mockito.when(mockCarService.create(Mockito.any())).thenReturn(recordWithId);
        ResponseEntity<Car> response = carController.createCar(input);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(recordWithId, response.getBody());
    }

    @Test
    public void getAllCars_shouldReturnListOfCarsAndOKHttpStatus() {
        List<Car> cars = new ArrayList<>();
        cars.add(input);
        cars.add(input2);
        Mockito.when(mockCarService.getAll()).thenReturn(cars);
        ResponseEntity<List<Car>> response = carController.getAllCars();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cars, response.getBody());
    }

    @Test
    public void getCarById_shouldReturnCarAndOKHttpStatus() {
        Mockito.when(mockCarService.getById(recordWithId.getId())).thenReturn(recordWithId);
        ResponseEntity<Car> response = carController.getCarById(recordWithId.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(recordWithId, response.getBody());
    }

    @Test
    public void getCarById_shouldReturn404WhenCarNotFound() {
        Mockito.when(mockCarService.getById(id)).thenThrow(new CarNotFoundException("A car with id: " + id + " was not found."));
        ResponseEntity<Car> response = carController.getCarById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void getCarByModel_shouldReturnListOfCarsAndOKHttpStatus() {
        Mockito.when(mockCarService.getByModel(recordWithId.getModel())).thenReturn(List.of(recordWithId));
        ResponseEntity<List<Car>> response = carController.getCarByModel(recordWithId.getModel());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(List.of(recordWithId), response.getBody());
    }

    @Test
    public void updateCar_shouldReturnCarAndOKHttpStatus() {
        Mockito.when(mockCarService.update(input2, recordWithId.getId())).thenReturn(recordWithId2);
        ResponseEntity<Car> response = carController.updateCar(input2, recordWithId.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(recordWithId2, response.getBody());
    }

    @Test
    public void updateCar_shouldReturn404WhenCarNotFound() {
        Mockito.when(mockCarService.update(input, id)).thenThrow(new CarNotFoundException("A car with id: " + id + " was not found."));
        ResponseEntity<Car> response = carController.updateCar(input, id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void patchCar_shouldReturnCarAndOKHttpStatus() {
        Mockito.when(mockCarService.patch(input2, recordWithId.getId())).thenReturn(recordWithId2);
        ResponseEntity<Car> response = carController.patchCar(input2, recordWithId.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(recordWithId2, response.getBody());
    }

    @Test
    public void patchCar_shouldReturn404WhenCarNotFound() {
        Mockito.when(mockCarService.patch(input, id)).thenThrow(new CarNotFoundException("A car with id: " + id + " was not found."));
        ResponseEntity<Car> response = carController.patchCar(input, id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void deleteCar_shouldReturnOKHttpStatus() {
        ResponseEntity<Car> response = carController.deleteCar(recordWithId.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}