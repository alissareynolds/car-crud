package com.example.car_crud.service;

import com.example.car_crud.exception.CarNotFoundException;
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

    private CarService carService;
    private CarRepository mockCarRepository;

    private final Car input = new Car(null, "Ford", "Ranger", "white", 1997, true, true);
    private final Car input2 = new Car(null, "Toyota", "Camry", "black", 2020, true, false);
    private final Car recordWithId = new Car(UUID.randomUUID(), "Ford", "Ranger", "white", 1997, true, true);
    private final Car recordWithId2 = new Car(recordWithId.getId(), "Toyota", "Camry", "black", 2020, true, false);

    public final UUID id = UUID.fromString("59c47568-fde0-4dd7-9aef-03db6a962810");

    @BeforeEach
    public void setup() {
        mockCarRepository = Mockito.mock(CarRepository.class);
        carService = new CarService(mockCarRepository);
    }

    @Test
    public void create_shouldReturnCreatedCar() {
        Mockito.when(mockCarRepository.save(Mockito.any())).thenReturn(recordWithId);
        Car response = carService.create(input);
        assertEquals(recordWithId, response);
    }

    @Test
    public void getAll_shouldReturnListOfCars() {
        List<Car> cars = new ArrayList<>();
        cars.add(input);
        cars.add(input2);
        Mockito.when(mockCarRepository.findAll()).thenReturn(cars);
        List<Car> response = carService.getAll();
        assertEquals(cars, response);
    }

    @Test
    public void getById_shouldReturnCar() {
        Mockito.when(mockCarRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Car response = carService.getById(recordWithId.getId());
        assertEquals(recordWithId, response);
    }

    @Test
    public void getById_throwsExceptionWhenBookWasNotFound() {
        Mockito.when(mockCarRepository.findById(id)).thenReturn(Optional.empty());
        CarNotFoundException exception = assertThrows(CarNotFoundException.class, () -> carService.getById(id));
        assertEquals("A car with id: " + id + " was not found.", exception.getMessage());
    }

    @Test
    public void getByModel_shouldReturnListOfCars() {
        Mockito.when(mockCarRepository.findByModel(recordWithId.getModel())).thenReturn(List.of(recordWithId));
        List<Car> response = carService.getByModel(recordWithId.getModel());
        assertEquals(List.of(recordWithId), response);
    }

    @Test
    public void update_shouldReturnUpdatedCar() {
        Mockito.when(mockCarRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Mockito.when(mockCarRepository.save(Mockito.any())).thenReturn(recordWithId);
        Car response = carService.update(input2, recordWithId.getId());
        assertEquals(recordWithId, response);
    }

    @Test
    public void update_throwsExceptionWhenCarNotFound() {
        Mockito.when(mockCarRepository.findById(id)).thenReturn(Optional.empty());
        CarNotFoundException exception = assertThrows(CarNotFoundException.class, () -> carService.update(input, id));
        assertEquals("A car with id: " + id + " was not found.", exception.getMessage());
    }

    @Test
    public void patch_throwsExceptionWhenCarNotFound() {
        Mockito.when(mockCarRepository.findById(id)).thenReturn(Optional.empty());
        CarNotFoundException exception = assertThrows(CarNotFoundException.class, () -> carService.patch(input, id));
        assertEquals("A car with id: " + id + " was not found.", exception.getMessage());
    }

    @Test
    public void patch_shouldReturnUpdatedModel() {
        Car input = new Car();
        input.setModel("Toyota");
        Mockito.when(mockCarRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Mockito.when(mockCarRepository.save(Mockito.any())).thenAnswer(i -> i.getArguments()[0]);
        Car response = carService.patch(input, recordWithId.getId());
        assertEquals("Toyota", response.getModel());
        assertEquals("Ranger", response.getMake());
        assertEquals("white", response.getColor());
        assertEquals(1997, response.getYear());
        assertEquals(true, response.getHasAirConditioning());
        assertEquals(true, response.getIsTruck());
    }

    @Test
    public void patch_shouldReturnUpdatedMake() {
        Car input = new Car();
        input.setMake("Camry");
        Mockito.when(mockCarRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Mockito.when(mockCarRepository.save(Mockito.any())).thenAnswer(i -> i.getArguments()[0]);
        Car response = carService.patch(input, recordWithId.getId());
        assertEquals("Ford", response.getModel());
        assertEquals("Camry", response.getMake());
        assertEquals("white", response.getColor());
        assertEquals(1997, response.getYear());
        assertEquals(true, response.getHasAirConditioning());
        assertEquals(true, response.getIsTruck());
    }

    @Test
    public void patch_shouldReturnUpdatedColor() {
        Car input = new Car();
        input.setColor("black");
        Mockito.when(mockCarRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Mockito.when(mockCarRepository.save(Mockito.any())).thenAnswer(i -> i.getArguments()[0]);
        Car response = carService.patch(input, recordWithId.getId());
        assertEquals("Ford", response.getModel());
        assertEquals("Ranger", response.getMake());
        assertEquals("black", response.getColor());
        assertEquals(1997, response.getYear());
        assertEquals(true, response.getHasAirConditioning());
        assertEquals(true, response.getIsTruck());
    }

    @Test
    public void patch_shouldReturnUpdatedYear() {
        Car input = new Car();
        input.setYear(2000);
        Mockito.when(mockCarRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Mockito.when(mockCarRepository.save(Mockito.any())).thenAnswer(i -> i.getArguments()[0]);
        Car response = carService.patch(input, recordWithId.getId());
        assertEquals("Ford", response.getModel());
        assertEquals("Ranger", response.getMake());
        assertEquals("white", response.getColor());
        assertEquals(2000, response.getYear());
        assertEquals(true, response.getHasAirConditioning());
        assertEquals(true, response.getIsTruck());
    }

    @Test
    public void patch_shouldReturnUpdatedHasAirConditioning() {
        Car input = new Car();
        input.setHasAirConditioning(false);
        Mockito.when(mockCarRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Mockito.when(mockCarRepository.save(Mockito.any())).thenAnswer(i -> i.getArguments()[0]);
        Car response = carService.patch(input, recordWithId.getId());
        assertEquals("Ford", response.getModel());
        assertEquals("Ranger", response.getMake());
        assertEquals("white", response.getColor());
        assertEquals(1997, response.getYear());
        assertEquals(false, response.getHasAirConditioning());
        assertEquals(true, response.getIsTruck());
    }

    @Test
    public void patch_shouldReturnUpdatedIsTruck() {
        Car input = new Car();
        input.setIsTruck(false);
        Mockito.when(mockCarRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Mockito.when(mockCarRepository.save(Mockito.any())).thenAnswer(i -> i.getArguments()[0]);
        Car response = carService.patch(input, recordWithId.getId());
        assertEquals("Ford", response.getModel());
        assertEquals("Ranger", response.getMake());
        assertEquals("white", response.getColor());
        assertEquals(1997, response.getYear());
        assertEquals(true, response.getHasAirConditioning());
        assertEquals(false, response.getIsTruck());
    }

    @Test
    public void delete_callsRepositoryDeleteMethod() {
        carService.delete(id);
        Mockito.verify(mockCarRepository).deleteById(id);
    }
}