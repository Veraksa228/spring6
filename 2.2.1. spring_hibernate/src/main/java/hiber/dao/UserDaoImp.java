package hiber.dao;


import hiber.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp {

    @PersistenceContext
    private EntityManager entityManager;


    public void add(User user) {
        entityManager.persist(user);
    }

    public void removeUser(User user) {
        entityManager.remove(user);
    }

    public List<User> getUsers() {
        return entityManager.createQuery("SELECT u FROM User u ", User.class).getResultList();
    }



}
