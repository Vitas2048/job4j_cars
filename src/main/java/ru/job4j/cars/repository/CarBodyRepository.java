package ru.job4j.cars.repository;

import ru.job4j.cars.model.CarBody;

import java.util.List;
import java.util.Optional;

public interface CarBodyRepository {

    Optional<CarBody> findById(int id);

    List<CarBody> findAll();
}
