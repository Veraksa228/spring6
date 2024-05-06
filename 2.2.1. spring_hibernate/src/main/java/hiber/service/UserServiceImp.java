package hiber.service;

import hiber.dao.UserDao;
import hiber.dao.UserDaoImp;

import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp {
    @Autowired
    private UserDaoImp userDao;


    @Transactional
    public void add(User user) {
        userDao.add(user);
    }
    @Transactional
    public void removeUser(User user){
        userDao.removeUser(user);
    }
    public List<User> getUsers() {
        return userDao.getUsers();
    }



}
