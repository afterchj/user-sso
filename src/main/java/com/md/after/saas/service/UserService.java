package com.md.after.saas.service;

import com.md.after.saas.entity.User;

import java.util.List;

public interface UserService {
    User selectByUsername(String userName);

    void save(User user);

    List<User> selectAll();
}
