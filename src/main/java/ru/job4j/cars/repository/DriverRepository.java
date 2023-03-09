package ru.job4j.cars.repository;

import ru.job4j.cars.model.Driver;
import ru.job4j.cars.model.User;

import java.util.List;
import java.util.Optional;

public interface DriverRepository {

    Driver create(Driver driver);

    Optional<Driver> findById(int id);

    Optional<Driver> findByUserId(int userId);

    List<Driver> findAll();

}
