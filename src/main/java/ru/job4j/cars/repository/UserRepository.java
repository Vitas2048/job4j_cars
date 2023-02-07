package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.job4j.cars.model.User;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserRepository {
    private final SessionFactory sf;

    public User create(User user) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
        return user;
    }

    public User update(User user) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
        return user;
    }

    public void delete(int userId) {
        Session session = sf.openSession();
        session.beginTransaction();
        User user = new User();
        user.setId(userId);
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

    public List<User> findAllOrderById() {
        Session session = sf.openSession();
        session.beginTransaction();
        List<User> users = session.createQuery("from User", User.class).list();
        session.getTransaction().commit();
        session.close();
        return users;
    }

    public Optional<User> findById(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        User res = session.get(User.class, id);
        session.getTransaction().commit();
        session.close();
        return Optional.ofNullable(res);
    }

    public List<User> findByLikeLogin(String key) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query<User> query = session.createQuery("from User as u where login like :fLogin", User.class)
                .setParameter("fLogin", key);
        List<User> res = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return res;
    }

    public Optional<User> findByLogin(String login) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query<User> query = session.createQuery("from User as u where u.login=:fLogin", User.class)
                .setParameter("fLogin", login);
        Optional<User> res = query.uniqueResultOptional();
        session.close();
        return res;
    }
}
