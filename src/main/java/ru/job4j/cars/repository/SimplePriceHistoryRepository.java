package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.PriceHistory;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class SimplePricceHistoryRepository implements PriceHistoryRepository {
    @Override
    public Optional<PriceHistory> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<PriceHistory> findAll() {
        return null;
    }
}
