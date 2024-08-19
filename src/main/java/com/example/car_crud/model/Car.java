package com.example.car_crud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity(name = "cars")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String model;

    private String make;

    private String color;

    @Column(name = "date")
    private Integer year;

    private Boolean hasAirConditioning;

    private Boolean isTruck;

    public Car(String model, String make, String color, Integer year, Boolean hasAirConditioning, Boolean isTruck) {
        this.model = model;
        this.make = make;
        this.color = color;
        this.year = year;
        this.hasAirConditioning = hasAirConditioning;
        this.isTruck = isTruck;
    }
//
//    public UUID getId() {
//        return id;
//    }
//
//    public void setId(UUID id) {
//        this.id = id;
//    }
//
//    public String getModel() {
//        return model;
//    }
//
//    public void setModel(String model) {
//        this.model = model;
//    }
//
//    public String getMake() {
//        return make;
//    }
//
//    public void setMake(String make) {
//        this.make = make;
//    }
//
//    public String getColor() {
//        return color;
//    }
//
//    public void setColor(String color) {
//        this.color = color;
//    }
//
//    public Integer getYear() {
//        return year;
//    }
//
//    public void setYear(Integer year) {
//        this.year = year;
//    }
//
//    public Boolean getHasAirConditioning() {
//        return hasAirConditioning;
//    }
//
//    public void setHasAirConditioning(Boolean hasAirConditioning) {
//        this.hasAirConditioning = hasAirConditioning;
//    }
//
//    public Boolean getIsTruck() {
//        return isTruck;
//    }
//
//    public void setIsTruck(Boolean isTruck) {
//        this.isTruck = isTruck;
//    }
}
