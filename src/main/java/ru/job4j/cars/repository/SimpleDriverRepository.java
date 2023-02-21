package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Driver;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@AllArgsConstructor
@Repository
public class SimpleDriverRepository implements DriverRepository {

    private CrudRepository crudRepository;

    @Override
    public Optional<Driver> findById(int id) {
        return crudRepository.optional("from Driver where id=:fId", Driver.class, Map.of("fId", id));
    }

    @Override
    public List<Driver> findAll() {
        return crudRepository.query("from Driver", Driver.class);
    }
}
