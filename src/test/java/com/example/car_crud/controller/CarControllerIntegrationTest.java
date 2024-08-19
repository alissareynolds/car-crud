package com.example.car_crud.controller;

import com.example.car_crud.model.Car;
import com.example.car_crud.service.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarService mockCarService;

    private final Car car = new Car(UUID.fromString("59c47568-fde0-4dd7-9aef-03db6a962810"), "Ford", "Ranger", "white", 1997, true, true);

    @Test
    public void createCar() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/cars")
                .content(asJsonString(new Car(null, "Ford", "Ranger", "white", 1997, true, true)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
