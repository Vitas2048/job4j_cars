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

    public static final String FIND_BY_ID_QUERY = "from File where id=:fId";

    public static final String FIND_ALL_QUERY = "from File";

    private CrudRepository crudRepository;

    @Override
    public List<File> findAll() {
        return crudRepository.query(FIND_ALL_QUERY, File.class);
    }

    @Override
    public Optional<File> findById(int id) {
        return crudRepository.optional(FIND_BY_ID_QUERY, File.class, Map.of("fId", id));
    }

    @Override
    public File save(File file) {
        crudRepository.run(session -> session.persist(file));
        return file;
    }
}
