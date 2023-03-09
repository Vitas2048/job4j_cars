package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Mark;

@Repository
@AllArgsConstructor
public class SimpleMarkRepository implements MarkRepository {

    private CrudRepository crudRepository;

    @Override
    public Mark create(Mark mark) {
        crudRepository.run(session -> session.persist(mark));
        return mark;
    }
}
