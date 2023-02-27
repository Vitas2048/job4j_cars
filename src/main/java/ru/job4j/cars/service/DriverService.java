package ru.job4j.cars.service;

import ru.job4j.cars.model.Driver;

import java.util.List;
import java.util.Optional;

public interface DriverService {
    Optional<Driver> findById(int id);

    List<Driver> findAll();
}
