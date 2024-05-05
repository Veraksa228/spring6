package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {


    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void addCar(Car user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> getUserByCar(String model, int series) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("FROM User u WHERE u.car.model = :model AND u.car.series = :series", User.class)
                .setParameter("model", model)
                .setParameter("series", series)
                .getResultList();

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("FROM User")
                .getResultList();
    }

}
