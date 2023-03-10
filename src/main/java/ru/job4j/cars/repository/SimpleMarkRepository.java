package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Mark;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class SimpleMarkRepository implements MarkRepository {

    private CrudRepository crudRepository;

    @Override
    public Mark create(Mark mark) {
        crudRepository.run(session -> session.persist(mark));
        return mark;
    }

    @Override
    public Optional<Mark> findById(int id) {
        return crudRepository.optional("""
                from Mark
                where id=:fId
                """, Mark.class, Map.of("fId", id));
    }
}
