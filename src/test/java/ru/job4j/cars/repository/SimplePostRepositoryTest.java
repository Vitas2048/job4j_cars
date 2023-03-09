package ru.job4j.cars.repository;

import org.checkerframework.checker.units.qual.C;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class SimplePostRepositoryTest {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    private final CrudRepository crudRepository = new CrudRepository(sf);

    @Test
    public void whenFindByLastDay() throws Exception {
        PostRepository repository = new SimplePostRepository(crudRepository);
        Post post = new Post();
        User user = new User();
        post.setCreated(LocalDateTime.now());
        post.setDescription("1");
        post.getHistory().add(new PriceHistory());
        post.getParticipates().add(user);
        post.setUser(user);
        post.getPictures().add(new File());
        post.setMark(new Mark());
        Post post1 = new Post();
        post1.setCreated(LocalDateTime.now().minusDays(20));
        repository.create(post);
        repository.create(post1);
        assertThat(repository.findByLastDay().get(0), is(post));
    }

    @Test
    public void whenFindByMark() throws Exception {
        PostRepository repository = new SimplePostRepository(crudRepository);
        Mark mark = new Mark();
        mark.setName("Honda");
        Post post = new Post();
//        User user = new User();
//        post.getParticipates().add(user);
        post.setCreated(LocalDateTime.now());
        post.setMark(mark);
        repository.create(post);
        assertThat(repository.findByMark(mark), is(post));
    }

    @Test
    public void whenFindWithIMG() throws Exception {
        PostRepository repository = new SimplePostRepository(crudRepository);
        Post post = new Post();
        File file = new File();
//        User user = new User();
//        post.getParticipates().add(user);
        file.setPath("users/file.jpg");
        List<File> files = new ArrayList<>();
        files.add(file);
        post.setCreated(LocalDateTime.now());
        post.setPictures(files);
        Post post1 = new Post();
        post.setCreated(LocalDateTime.now());
        repository.create(post);
        repository.create(post1);
        assertThat(repository.findWithImg().get(0), is(post));
    }
}