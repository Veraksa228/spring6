package hiber.service;

import hiber.dao.UserDao;
import hiber.dao.UserDaoImp;

import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImp {
    @Autowired
    private UserDaoImp userDao;



    public void add(User user) {
        userDao.add(user);
    }

    public void removeUser(User user){
        userDao.removeUser(user);
    }
    public List<User> getUsers() {
        return userDao.getUsers();
    }
    public User findUser(Long id){
      return   userDao.findUser(id);
    }
    public void updateUser(Long userId, String firstName, String lastName, String email) {

        Optional<User> optionalUser = Optional.ofNullable(userDao.findUser(userId));
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);

            userDao.add(user);
        } else {

            throw new RuntimeException("User not found with id: " + userId);
        }
    }
    public void updateUser(User user) {
        userDao.updateUser(user);
    }


}
