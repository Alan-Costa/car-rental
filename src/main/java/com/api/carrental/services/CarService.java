package com.api.carrental.services;

import com.api.carrental.models.Car;
import com.api.carrental.repositories.CarRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Transactional
    public Car save(Car car) {
        return carRepository.save(car);
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public Optional<Car> findById(Long id) {
        return carRepository.findById(id);
    }

    @Transactional
    public void delete(Car car) {
        carRepository.delete(car);
    }


}
