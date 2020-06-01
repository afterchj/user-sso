package com.md.after.saas.service.impl;

import com.md.after.saas.dao.UserDao;
import com.md.after.saas.entity.User;
import com.md.after.saas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User selectByUsername(String userName) {
        return userDao.selectByUsername(userName);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public List<User> selectAll() {
        return userDao.selectAll();
    }
}
