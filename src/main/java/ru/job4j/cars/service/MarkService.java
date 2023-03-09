package ru.job4j.cars.service;

import ru.job4j.cars.model.Mark;

import java.util.List;
import java.util.Optional;

public interface MarkService {
    List<Mark> findAll();

    Optional<Mark> findById(int id);
}
