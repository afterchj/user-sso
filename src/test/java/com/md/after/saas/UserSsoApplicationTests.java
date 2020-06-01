package com.md.after.saas;

import com.alibaba.fastjson.JSON;
import com.md.after.saas.entity.User;
import com.md.after.saas.entity.UserRole;
import com.md.after.saas.service.UserRoleService;
import com.md.after.saas.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserSsoApplicationTests {

    private Logger logger = LoggerFactory.getLogger(UserSsoApplicationTests.class);
    @Autowired
    UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;


    @Test
    public void contextLoads() {
        User user = sqlSessionTemplate.selectOne("com.md.after.saas.dao.UserDao.selectByUsername", "hongjian.chen");
//        List<User> users = userService.selectAll();
//        logger.warn("/selectAll {}", JSON.toJSON(users));
        UserRole userRole = new UserRole();
        userRole.setUserId("20");
        userRole.setRoleId(2);
        userRoleService.insert(userRole);
        logger.warn("id {}", userRole.getId());
//        logger.warn("Hello {}", user);
    }
}
