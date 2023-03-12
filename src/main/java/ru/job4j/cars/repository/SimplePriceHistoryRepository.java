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

    public static final String FIND_BY_ID_QUERY = "from PriceHistory where id=:fId";

    public static final String FIND_ALL_QUERY = "from PriceHistory";
    private CrudRepository crudRepository;

    @Override
    public Optional<PriceHistory> findById(int id) {
        return crudRepository.optional(FIND_BY_ID_QUERY, PriceHistory.class, Map.of("fId", id));
    }

    @Override
    public List<PriceHistory> findAll() {
        return crudRepository.query(FIND_ALL_QUERY, PriceHistory.class);
    }
}
