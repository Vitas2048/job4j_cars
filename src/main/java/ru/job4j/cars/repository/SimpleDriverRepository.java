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

    public static final String FIND_BY_ID_QUERY = "from Driver d left join fetch d.user where d.id=:fId";

    public static final String FIND_BY_USER_ID_QUERY = "from Driver d left join fetch d.user where user_id=:fId";

    public static final String FIND_ALL_QUERY = "from Driver";

    private CrudRepository crudRepository;

    @Override
    public Optional<Driver> findById(int id) {
        return crudRepository.optional(FIND_BY_ID_QUERY, Driver.class, Map.of("fId", id));
    }

    @Override
    public Optional<Driver> findByUserId(int userId) {
        return crudRepository.optional(FIND_BY_USER_ID_QUERY, Driver.class, Map.of("fId", userId));
    }

    @Override
    public List<Driver> findAll() {
        return crudRepository.query(FIND_ALL_QUERY, Driver.class);
    }

    @Override
    public Driver create(Driver driver) {
        crudRepository.run(session -> session.persist(driver));
        return driver;
    }
}
