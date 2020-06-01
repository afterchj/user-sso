package com.md.after.saas.dao;

import com.md.after.saas.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleDao {
    List<Role> getAll();
}
