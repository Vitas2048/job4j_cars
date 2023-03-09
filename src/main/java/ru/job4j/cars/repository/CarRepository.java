package ru.job4j.cars.repository;

import ru.job4j.cars.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarRepository {

    Optional<Car> findById(int id);

    List<Car> findAll();

    Optional<Car> save(Car car);
}
