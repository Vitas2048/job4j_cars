package ru.job4j.cars.service;

import ru.job4j.cars.model.PriceHistory;

import java.util.List;
import java.util.Optional;

public interface PriceHistoryService {

    List<PriceHistory> findAll();

    Optional<PriceHistory> findById(int id);

}
