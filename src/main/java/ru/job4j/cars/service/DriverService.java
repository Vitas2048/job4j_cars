package ru.job4j.cars.service;

import ru.job4j.cars.model.Driver;

import java.util.List;
import java.util.Optional;

public interface DriverService {

    Driver create(Driver driver);

    Optional<Driver> findById(int id);

    List<Driver> findAll();

    Optional<Driver> findByUserId(int userId);
}
