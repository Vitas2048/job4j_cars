package ru.job4j.cars.service;

import ru.job4j.cars.model.CarBody;

import java.util.List;
import java.util.Optional;

public interface CarBodyService {
    Optional<CarBody> findById(int id);

    List<CarBody> findAll();
}
