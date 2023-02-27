package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.model.File;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class SimpleFileRepository implements FileRepository {
    private CrudRepository crudRepository;

    @Override
    public List<File> findAll() {
        return crudRepository.query("from File", File.class);
    }

    @Override
    public Optional<File> findById(int id) {
        return crudRepository.optional("from File where id=:fId", File.class, Map.of("fId", id));
    }
}
