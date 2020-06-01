package com.md.after.saas.dao;


import com.md.after.saas.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleDao {

    void insert(UserRole userRole);
}
