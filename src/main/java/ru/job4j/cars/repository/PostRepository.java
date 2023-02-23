package ru.job4j.cars.repository;

import ru.job4j.cars.model.Mark;
import ru.job4j.cars.model.Post;

import java.util.List;

public interface PostRepository {

    List<Post> findByLastDay();

    List<Post> findWithImg();

    List<Post> findByMark(Mark mark);


}
