package hiber.dao;


import hiber.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

        User managedUser = entityManager.merge(user);

        entityManager.remove(managedUser);
    }

    public List<User> getUsers() {
        return entityManager.createQuery("SELECT u FROM User u ", User.class).getResultList();
    }
    public User findUser(Long id){
       return entityManager.find(User.class,id);

    }
    @Transactional
    public void updateUser(User updatedUser) {

        if (updatedUser.getId() != null) {

            User existingUser = entityManager.find(User.class, updatedUser.getId());
            if (existingUser != null) {

                existingUser.setFirstName(updatedUser.getFirstName());
                existingUser.setLastName(updatedUser.getLastName());
                existingUser.setEmail(updatedUser.getEmail());

                entityManager.merge(existingUser);
            } else {

            }
        } else {
            throw new IllegalArgumentException("Not User ID");
        }
    }



}
