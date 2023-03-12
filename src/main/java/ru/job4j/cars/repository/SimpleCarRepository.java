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

    public static final String FIND_BY_ID_QUERY = """
                from Car c
                left join fetch c.engine 
                left join fetch c.mark 
                left join fetch c.drivers 
                where c.id = :fId
                """;

    public static final String FIND_ALL_QUERY = """
                from Car c
                left join fetch c.engine
                left join fetch c.mark
                left join fetch c.drivers
                left join fetch c.carBody
                """;

    private CrudRepository crudRepository;

    @Override
    public Optional<Car> findById(int id) {
        return crudRepository.optional(FIND_BY_ID_QUERY, Car.class, Map.of("fId", id));
    }

    @Override
    public List<Car> findAll() {
        return crudRepository.query(FIND_ALL_QUERY, Car.class);
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
