package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.model.PriceHistory;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class SimplePriceHistoryRepository implements PriceHistoryRepository {

    private CrudRepository crudRepository;

    @Override
    public Optional<PriceHistory> findById(int id) {
        return crudRepository.optional("from PriceHistory where id=:fId", PriceHistory.class, Map.of("fId", id));
    }

    @Override
    public List<PriceHistory> findAll() {
        return crudRepository.query("from PriceHistory", PriceHistory.class);
    }
}
