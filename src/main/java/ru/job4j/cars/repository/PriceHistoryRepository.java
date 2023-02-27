package ru.job4j.cars.repository;

import ru.job4j.cars.model.PriceHistory;

import java.util.List;
import java.util.Optional;

public interface PriceHistoryRepository {

    List<PriceHistory> findAll();

    Optional<PriceHistory> findById(int id);
}
