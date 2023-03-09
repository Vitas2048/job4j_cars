package ru.job4j.cars.repository;

import ru.job4j.cars.model.Mark;
import ru.job4j.cars.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    List<Post> findByLastDay();

    List<Post> findWithImg();

    List<Post> findByMark(int markId);

    Post create(Post post);

    List<Post> findAll();

    Optional<Post> findById(int id);

    void update(Post post);

    List<Post> findByMarkAndCarBody(int markId, int carBodyId);

    List<Post> findByCarBodyId(int carBodyId);
}
