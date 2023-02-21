package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@Repository
@AllArgsConstructor
public class SimpleCarRepository implements CarRepository {

    private CrudRepository crudRepository;

    @Override
    public Optional<Car> findById(int id) {
        return crudRepository.optional("from Car where id = :fId", Car.class, Map.of("fId", id));
    }

    @Override
    public List<Car> findAll() {
        return crudRepository.query("from car", Car.class);
    }
}
