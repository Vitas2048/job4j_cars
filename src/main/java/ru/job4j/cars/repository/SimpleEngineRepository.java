package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Engine;

import javax.persistence.Entity;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Repository
@AllArgsConstructor
public class SimpleEngineRepository implements EngineRepository {

    public static final String FIND_BY_ID_QUERY = "from Engine where id=:fId";

    public static final String FIND_ALL_QUERY = "from Engine";

    private CrudRepository crudRepository;

    @Override
    public Optional<Engine> findById(int id) {
        return crudRepository.optional(FIND_BY_ID_QUERY, Engine.class, Map.of("fId", id));
    }

    @Override
    public List<Engine> findAll() {
        return crudRepository.query(FIND_ALL_QUERY, Engine.class);
    }
}
