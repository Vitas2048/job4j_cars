package ru.job4j.cars.service;

import ru.job4j.cars.model.Mark;
import ru.job4j.cars.model.Post;

import java.util.List;

public interface PostService {

    List<Post> findByLastDay();

    List<Post> findWithImg();

    List<Post> findByMark(Mark mark);

    Post create(Post post);

}
