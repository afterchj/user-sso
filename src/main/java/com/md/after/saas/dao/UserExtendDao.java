package com.md.after.saas.dao;



import com.md.after.saas.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserExtendDao {

    User selectByUsername(Map map);

    User selectByUsername(String uname);

    List<String> getRoles(String uname);

    List<String> getPermissions(String uname);
}
