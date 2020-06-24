package com.md.after.saas.controller;

import com.alibaba.fastjson.JSON;
import com.md.after.saas.entity.User;
import com.md.after.saas.entity.UserRole;
import com.md.after.saas.model.ResultDict;
import com.md.after.saas.realm.EasyTypeToken;
import com.md.after.saas.service.UserRoleService;
import com.md.after.saas.service.UserService;
import com.md.after.saas.utils.Encryption;
import org.apache.commons.lang3.StringUtils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping()
    public String login() {
        return "login";
    }

    @RequestMapping("/addUI")
    public String newUser() {
        return "addUI";
    }

    @GetMapping("/forbid")
    public String forbid() {
        return "forbidden";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, HttpSession session, ModelMap map) {
        logger.warn("userName {} pwd {}", user.getUname(), user.getPwd());
        String userName = user.getUname();
        String pwd = user.getPwd();
        String mobile = user.getMobile();
        String email = user.getEmail();
        EasyTypeToken token;
        if (StringUtils.isNotEmpty(mobile)) {
            userName = mobile;
            token = new EasyTypeToken(userName);
        } else if (StringUtils.isNotEmpty(email)) {
            userName = email;
            token = new EasyTypeToken(userName);
        } else {
            token = new EasyTypeToken(userName, Encryption.getMD5Str(pwd));
        }
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (Exception e) {
            logger.info("errMsg=" + e);
            if (e instanceof UnknownAccountException) {
                map.put("errMsg", e.getMessage());
                return "error";
            } else if (e instanceof LockedAccountException) {
                map.put("errMsg", e.getMessage());
                return "error";
            } else if (e instanceof DisabledAccountException) {
                map.put("errMsg", e.getMessage());
                return "error";
            } else if (e instanceof IncorrectCredentialsException) {
                map.put("errMsg", ResultDict.PASSWORD_NOT_CORRECT.getValue());
                return "error";
            }
        }
        User loginUser = userService.selectByUsername(userName);
        session.setAttribute("user", loginUser);
        return "success";
    }

    @RequestMapping("/create")
    public String create(User user) {
        UserRole userRole = new UserRole();
//        logger.warn("ids {}", ids);
        Encryption.HashPassword password = Encryption.encrypt(Encryption.getMD5Str(user.getPwd()));
        user.setPwd(password.getPassword());
        user.setSalt(password.getSalt());
        userService.save(user);
        logger.warn("id {}", user.getId());
        userRole.setUserId(user.getId());
        userRole.setRoleId(2);
        userRoleService.insert(userRole);
        return "redirect:list";
    }

    @RequestMapping("/list")
    public String selectAll(ModelMap modelMap) {
        List<User> users = userService.selectAll();
        logger.warn("/selectAll {}", JSON.toJSON(users));
        modelMap.put("users", users);
        return "list";
    }

    @RequiresPermissions("user:update")
    @RequestMapping("/updateUI")
    public String update() throws Exception {
        return "addUI";
    }
}
