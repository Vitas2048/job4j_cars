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
        return crudRepository.optional("""
                from Car c
                left join fetch c.engine 
                left join fetch c.mark 
                left join fetch c.drivers 
                where c.id = :fId
                """, Car.class, Map.of("fId", id));
    }

    @Override
    public List<Car> findAll() {
        return crudRepository.query("""
                from Car c
                left join fetch c.engine
                left join fetch c.mark
                left join fetch c.drivers
                left join fetch c.carBody
                """, Car.class);
    }

    @Override
    public Optional<Car> save(Car car) {
        try {
            crudRepository.run(session -> session.persist(car));
            return Optional.of(car);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
