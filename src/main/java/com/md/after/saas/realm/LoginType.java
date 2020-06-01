package com.md.after.saas.realm;

/**
 * Created by hongjian.chen on 2019/4/9.
 */
public enum LoginType {

    PASSWORD("password"), // 密码登录
    NOPASSWD("nopassword"); // 免密登录

    private String code;// 状态值

     LoginType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
