package com.md.after.saas.service.impl;

import com.md.after.saas.dao.RoleDao;
import com.md.after.saas.entity.Role;
import com.md.after.saas.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImple implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> getAll() {
        return roleDao.getAll();
    }
}
