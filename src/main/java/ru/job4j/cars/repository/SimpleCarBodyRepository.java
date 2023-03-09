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

    private CrudRepository crudRepository;


    @Override
    public Optional<CarBody> findById(int id) {
        return crudRepository.optional("from CarBody where id=:fId", CarBody.class, Map.of("fId", id));
    }

    @Override
    public List<CarBody> findAll() {
        return crudRepository.query("from CarBody", CarBody.class);
    }
}
