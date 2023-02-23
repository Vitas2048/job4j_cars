package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Mark;
import ru.job4j.cars.model.Post;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class SimplePostRepository implements PostRepository {

    private CrudRepository crudRepository;

    @Override
    public List<Post> findByLastDay() {
        var now = Timestamp.from(Instant.from(LocalDateTime.now()));
        var today = Timestamp.from(Instant.from(LocalDateTime.now().minusDays(1)));
        return crudRepository.query("from Post where created between :fNow and :fToday",
                Post.class, Map.of("fNow", now, "fToday", today));
    }

    @Override
    public List<Post> findWithImg() {
        return crudRepository.query("from Post where where file_id is not null", Post.class);
    }

    @Override
    public List<Post> findByMark(Mark mark) {
        return crudRepository.query("from Post where where mark_id = :fMark", Post.class, Map.of("fMark", mark.getId()));
    }
}
