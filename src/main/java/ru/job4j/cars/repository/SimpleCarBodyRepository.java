package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.CarBody;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class SimpleCarBodyRepository implements CarBodyRepository {

    private static final String FIND_BY_ID_QUERY = "from CarBody where id=:fId";

    private static final String QUERY = "from CarBody";

    private CrudRepository crudRepository;


    @Override
    public Optional<CarBody> findById(int id) {
        return crudRepository.optional(FIND_BY_ID_QUERY, CarBody.class, Map.of("fId", id));
    }

    @Override
    public List<CarBody> findAll() {
        return crudRepository.query(QUERY, CarBody.class);
    }
}
