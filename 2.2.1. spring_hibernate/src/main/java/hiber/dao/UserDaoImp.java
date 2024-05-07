package hiber.dao;


import hiber.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void removeUser(User user) {
        User managedUser = entityManager.merge(user);
        entityManager.remove(managedUser);
    }

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("SELECT u FROM User u ", User.class).getResultList();
    }

    @Override
    public User findUser(Long id) {
        return entityManager.find(User.class, id);

    }

    @Transactional
    @Override
    public void updateUser(User updatedUser) {
        if (updatedUser.getId() != null) {
            User existingUser = entityManager.find(User.class, updatedUser.getId());
            if (existingUser != null) {

                existingUser.setFirstName(updatedUser.getFirstName());
                existingUser.setLastName(updatedUser.getLastName());
                existingUser.setEmail(updatedUser.getEmail());

                entityManager.merge(existingUser);
            }
        } else {
            throw new IllegalArgumentException("Not User ID");
        }
    }


}
