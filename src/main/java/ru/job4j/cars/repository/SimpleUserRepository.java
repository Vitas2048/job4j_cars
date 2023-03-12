package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class SimpleUserRepository implements UserRepository {

    public static final String DELETE_QUERY = "delete from User where id =:fId";

    public static final String FIND_ALL_ORDER_BY_ID_QUERY = "from User order by id asc";

    public static final String FIND_BY_ID_QUERY = "from User where id = :fId";

    public static final String FIND_BY_LIKE_LOGIN_QUERY = "from User where login like :fKey";

    public static final String FIND_BY_LOGIN_QUERY = "from User where login = :fLogin";

    public static final String FIND_BY_LOGIN_AND_PASSWORD_QUERY =
            "from User u  where u.login = :fLogin and u.password=:fPassword";

    private final CrudRepository crudRepository;
    @Override
    public Optional<User> create(User user) {
        try {
            crudRepository.run(session -> session.persist(user));
            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
    @Override
    public void update(User user) {
        crudRepository.run(session -> session.merge(user));
    }

    @Override
    public void delete(int userId) {
        crudRepository.run(DELETE_QUERY, Map.of("fId", userId));
    }

    @Override
    public List<User> findAllOrderById() {
        return crudRepository.query(FIND_ALL_ORDER_BY_ID_QUERY, User.class);
    }

    @Override
    public Optional<User> findById(int id) {
        return crudRepository.optional(FIND_BY_ID_QUERY, User.class, Map.of("fId", id)
        );
    }

    @Override
    public List<User> findByLikeLogin(String key) {
        return crudRepository.query(FIND_BY_LIKE_LOGIN_QUERY, User.class, Map.of("fKey", "%" + key + "%"));
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return crudRepository.optional(FIND_BY_LOGIN_QUERY, User.class, Map.of("fLogin", login));
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        try {
            var user = crudRepository.optional(FIND_BY_LOGIN_AND_PASSWORD_QUERY,
                    User.class, Map.of("fLogin", login, "fPassword", password));
            return user;
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
