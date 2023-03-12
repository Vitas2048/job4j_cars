package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.model.Mark;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class SimpleMarkRepository implements MarkRepository {

    public static final String FIND_BY_ID_QUERY = "from Mark where id=:fId";

    public static final String FIND_ALL_QUERY = "from Mark";

    private CrudRepository crudRepository;

    @Override
    public Mark create(Mark mark) {
        crudRepository.run(session -> session.persist(mark));
        return mark;
    }
    public List<Mark> findAll() {
        return crudRepository.query(FIND_ALL_QUERY, Mark.class);
    }

    @Override
    public Optional<Mark> findById(int id) {
        return crudRepository.optional(FIND_BY_ID_QUERY, Mark.class, Map.of("fId", id));
    }
}
