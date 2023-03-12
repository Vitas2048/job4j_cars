package ru.job4j.cars.service;

import ru.job4j.cars.dto.FileDto;
import ru.job4j.cars.dto.PostDto;
import ru.job4j.cars.model.Mark;
import ru.job4j.cars.model.Post;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PostService {

    List<Post> findByLastDay();

    List<Post> findWithImg();

    List<Post> findByMark(int markId);

    Post create(Post post);

    List<Post> findAll();

    Post save(Post post, FileDto fileDto);

    Optional<Post> findById(int id);

    void update(Post post);

    void updateWithFile(Post post, FileDto fileDto);

    List<Post> findByMarkAndCarBody(int markId, int carBodyId);

    List<Post> findByCarBodyId(int carBodyId);

    Set<PostDto> toDtoSet(List<Post> posts);
}
