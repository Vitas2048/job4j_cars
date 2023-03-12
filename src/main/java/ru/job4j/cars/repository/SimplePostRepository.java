package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Post;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class SimplePostRepository implements PostRepository {

    public static final String FIND_BY_LAST_DAY_QUERY = """
                        from Post p
                        where p.created between :fToday and :fNow""";

    public static final String FIND_WITH_IMG = """
                from Post p
                left join fetch p.pictures m
                where m is not null""";

    public static final String FIND_BY_MARK_QUERY = """
                from Post p
                left join fetch p.user
                left join fetch p.car
                left join fetch p.pictures
                left join fetch p.carBody
                left join fetch p.mark m
                where m.id=:fMarkId
                """;

    public static final String FIND_ALL_QUERY = """
            from Post p
            left join fetch p.user
            left join fetch p.car
            left join fetch p.mark
            left join fetch p.pictures
            left join fetch p.carBody
            order by p.id
            """;

    public static final String FIND_BY_ID_QUERY = """
                from Post p
                left join fetch p.user
                left join fetch p.car
                left join fetch p.mark
                left join fetch p.pictures
                left join fetch p.carBody
                where p.id=:fId
                """;

    public static final String FIND_BY_MARK_AND_CAR_BODY_ID_QUERY = """
                from Post p
                left join fetch p.user
                left join fetch p.car
                left join fetch p.mark m
                left join fetch p.pictures
                left join fetch p.carBody c
                where c.id=:fCarBodyId and m.id=:fMarkId
                """;

    public static final String FIND_BY_CAR_BODY_ID_QUERY = """
                from Post p
                left join fetch p.user
                left join fetch p.car
                left join fetch p.mark
                left join fetch p.pictures
                left join fetch p.carBody c
                where c.id=:fCarBodyId
                """;

    private CrudRepository crudRepository;

    @Override
    public List<Post> findByLastDay() {
        var now = Timestamp.valueOf(LocalDateTime.now());
        var today = Timestamp.valueOf(LocalDateTime.now().minusDays(1));
        return crudRepository.query(FIND_BY_LAST_DAY_QUERY, Post.class, Map.of("fNow", now, "fToday", today));
    }

    @Override
    public List<Post> findWithImg() {
        return crudRepository.query(FIND_WITH_IMG, Post.class);
    }

    @Override
    public List<Post> findByMark(int markId) {
        return crudRepository.query(FIND_BY_MARK_QUERY, Post.class, Map.of("fMarkId", markId));
    }

    @Override
    public Post create(Post post) {
        crudRepository.run(session -> session.persist(post));
        return post;
    }

    @Override
    public List<Post> findAll() {
        return crudRepository.query(FIND_ALL_QUERY, Post.class);
    }

    @Override
    public Optional<Post> findById(int id) {
        return crudRepository.optional(FIND_BY_ID_QUERY, Post.class, Map.of("fId", id));
    }

    @Override
    public void update(Post post) {
        crudRepository.run(session -> session.merge(post));
    }

    @Override
    public List<Post> findByMarkAndCarBody(int markId, int carBodyId) {
        return crudRepository.query(FIND_BY_MARK_AND_CAR_BODY_ID_QUERY,
                Post.class, Map.of("fMarkId", markId, "fCarBodyId", carBodyId));
    }

    @Override
    public List<Post> findByCarBodyId(int carBodyId) {
        return crudRepository.query(FIND_BY_CAR_BODY_ID_QUERY, Post.class, Map.of("fCarBodyId", carBodyId));
    }
}