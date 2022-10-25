package com.api.carrental.controllers;

import com.api.carrental.dtos.CarDto;
import com.api.carrental.models.Car;
import com.api.carrental.services.CarService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<Object> saveCar(@RequestBody @Valid CarDto carDto) {
        var car = new Car();
        BeanUtils.copyProperties(carDto, car);
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.save(car));
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAllCar() {
        return ResponseEntity.status(HttpStatus.OK).body(carService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneCar(@PathVariable(value = "id") Long id) {
        Optional<Car> carOptional = carService.findById(id);
        if (!carOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(carOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCar(@PathVariable(value = "id") Long id) {
        Optional<Car> carOptional = carService.findById(id);
        if (!carOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found");
        }
        carService.delete(carOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Car deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCar(@PathVariable(value = "id") Long id, @RequestBody @Valid CarDto carDto) {
        Optional<Car> carOptional = carService.findById(id);
        if (!carOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found.");
        }
        var car = new Car();
        BeanUtils.copyProperties(carDto, car);
        car.setId(carOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(carService.save(car));
    }
}