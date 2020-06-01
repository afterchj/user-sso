package com.md.after.saas.service.impl;

import com.md.after.saas.dao.UserRoleDao;
import com.md.after.saas.entity.UserRole;
import com.md.after.saas.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public void insert(UserRole userRole) {
        userRoleDao.insert( userRole);
    }
}
