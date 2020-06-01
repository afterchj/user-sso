package com.md.after.saas.dao;


import com.md.after.saas.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {
    //
//    void deleteById(Integer id);
//
    int save(User user);
//
//    int insertSelective(User record);
//
    List<User> selectAll();
//
//    User selectById(String id);

    User selectByUsername(String uname);

//    void updatePwd(Map map);
//
//    int updateById(User record);
//
//    void blockUserById(Integer id);
//
//    void unblockUserById(Integer id);
//
//    int getCount(Map map);

}
