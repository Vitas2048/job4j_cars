package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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
        post.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        post.setDescription("1");
        Post post1 = new Post();
        post1.setDescription("1");
        post1.setCreated(Timestamp.valueOf(LocalDateTime.now().minusDays(20)));
        repository.create(post);
        repository.create(post1);
        var listFound = repository.findByLastDay();
        var found = listFound.get(listFound.size() - 1).getCreated();
        assertThat(found.getTime(), is(post.getCreated().getTime()));
    }

    @Test
    public void whenFindByMark() throws Exception {
        CrudRepository crudRepository = crudRepository();
        PostRepository repository = new SimplePostRepository(crudRepository());
        MarkRepository markRepository = new SimpleMarkRepository(crudRepository);
        Mark mark = new Mark();
        mark.setName("Honda");
        markRepository.create(mark);
        Post post = new Post();
        post.setDescription("1");
        repository.create(post);
        post.setMark(mark);
        repository.update(post);
        var listFound = repository.findByMark(mark);
        assertThat(listFound.get(listFound.size() - 1), is(post));
    }

    @Test
    public void whenFindWithIMG() throws Exception {
        CrudRepository crudRepository = crudRepository();
        PostRepository repository = new SimplePostRepository(crudRepository);
        Post post = new Post();
        File file = new File();
        file.setPath("users/file.jpg");
        post.getPictures().add(file);
        Post post1 = new Post();
        post.setDescription("1");
        post1.setDescription("1");
        repository.create(post);
        repository.create(post1);
        assertThat(repository.findWithImg().get(0), is(post));
    }
}