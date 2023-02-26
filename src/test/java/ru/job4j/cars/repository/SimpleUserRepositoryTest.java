package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.User;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class SimpleUserRepositoryTest {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    private final CrudRepository crudRepository = new CrudRepository(sf);

    @Test
    public void whenCreateUser() throws Exception {
        SimpleUserRepository repository = new SimpleUserRepository(crudRepository);
        User user = new User();
        user.setPassword("0000");
        user.setLogin("admin");
        ArrayList<User> users = new ArrayList<>(repository.findAllOrderById());
        repository.create(user);
        users.add(user);
        System.out.println(users.get(0).getId());
        repository.findAllOrderById().forEach(s -> System.out.println(s.getLogin()));
        List<User> res = repository.findAllOrderById();
        assertThat(users, is(res));
    }

    @Test
    public void whenDeleteUser() throws Exception {
        SimpleUserRepository repository = new SimpleUserRepository(crudRepository);
        User user = new User();
        user.setPassword("0000");
        user.setLogin("admin");
        var before = repository.findAllOrderById();
        repository.create(user);
        repository.delete(repository.findByLogin(user.getLogin()).get().getId());
        var after = repository.findAllOrderById();
        assertThat(before, is(after));
    }

    @Test
    public void whenFindLike() throws Exception {
        SimpleUserRepository repository = new SimpleUserRepository(crudRepository);
        User user = new User();
        user.setPassword("0000");
        user.setLogin("admin");
        User user1 = new User();
        user.setPassword("0000");
        user.setLogin("administrator");
        User user2 = new User();
        user.setPassword("0000");
        user.setLogin("admins");
        List<User> expect = new ArrayList<>();
        expect.add(user);
        expect.add(user1);
        expect.add(user2);
        repository.create(user);
        repository.create(user1);
        repository.create(user2);
        assertThat(expect.size(), is(repository.findByLikeLogin("admin").size()));
    }

    @Test
    public void whenUpdate() throws Exception {
        SimpleUserRepository repository = new SimpleUserRepository(crudRepository);
        User user = new User();
        user.setLogin("Login");
        user.setPassword("1234");
        repository.create(user);
        user.setPassword("0000");
        repository.update(user);
        assertThat(user, is(repository.findByLogin("Login").get()));
    }
}