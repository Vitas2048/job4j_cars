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

    private CrudRepository crudRepository;

    @Override
    public Optional<Engine> findById(int id) {
        return crudRepository.optional("from Engine where id=:fId", Engine.class, Map.of("fId", id));
    }

    @Override
    public List<Engine> findAll() {
        return crudRepository.query("from Engine", Engine.class);
    }
}
