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

    private CrudRepository crudRepository() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        return new CrudRepository(sf);
    }

    @Test
    public void whenFindByLastDay() throws Exception {
        CrudRepository crudRepository = crudRepository();
        PostRepository repository = new SimplePostRepository(crudRepository);
        Post post = new Post();
        post.setCreated(LocalDateTime.now());
        post.setDescription("1");
        Post post1 = new Post();
        post1.setDescription("1");
        post1.setCreated(LocalDateTime.now().minusDays(20));
        repository.create(post);
        repository.create(post1);
        var found = repository.findByLastDay().get(0);
        System.out.println(found);
        assertThat(found, is(post));
    }

    @Test
    public void whenFindByMark() throws Exception {
        CrudRepository crudRepository = crudRepository();
        PostRepository repository = new SimplePostRepository(crudRepository);
        MarkRepository markRepository = new SimpleMarkRepository(crudRepository);
        Mark mark = new Mark();
        mark.setName("Honda");
        markRepository.create(mark);
        Post post = new Post();
        post.setCreated(LocalDateTime.now());
        post.setMark(mark);
        post.setDescription("1");
        repository.create(post);
        assertThat(repository.findByMark(mark), is(post));
    }

    @Test
    public void whenFindWithIMG() throws Exception {
        CrudRepository crudRepository = crudRepository();
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