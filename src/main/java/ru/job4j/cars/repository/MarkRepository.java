package ru.job4j.cars.repository;

import ru.job4j.cars.model.Mark;

import java.util.List;
import java.util.Optional;

public interface MarkRepository {

    Mark create(Mark mark);

    List<Mark> findAll();

    Optional<Mark> findById(int id);
}
