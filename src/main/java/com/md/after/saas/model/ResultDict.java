package com.md.after.saas.model;

public enum ResultDict {

    SUCCESS("000", "成功"), SYSTEM_ERROR("200", "系统错误"),
    ACCOUNT_NOT_CORRECT("101", "登录名错误"), PASSWORD_NOT_CORRECT("102", "登录名密码不正确"), MOBILE_NOT_EXISTED("103", "该手机号未绑定！"), ACCOUNT_DISABLED("104", "该账号已禁用！"),ACCOUNT_LOCKEED("105", "该账号在别处登入！"),
    LIGHT_EXISTED("120", "有灯存在"), ID_REPEATED("121", "该账户下的meshId重复"), TOKEN_NOT_SUBMIT("107", "token没有提交"), TOKEN_NOT_CORRECT("108", "token失效"), TOKEN_REPLACED("109", "账号在其他设备登陆。"),
    UNAME_REPET("201", "该用户名已存在！"), VERIFY_ERROR("202", "验证码不正确！"), MOBILE_REPET("203", "该手机号已绑定！"), PARAMS_BLANK("204", "参数不能够为空"), PARAMS_NOT_PARSED("205", "参数解析错误"), MESHID_NOT_NULL("115", "未发现该网络"),
    NO_SCENE("301", "该用户或网络下没有创建场景"), NO_GROUP("302", "该用户或网络下没有创建组"),PROJECT_EXISTED("303", "名下有项目，跳转"),NOT_SAME_COMPANY("304", "不是同一个公司的账号"),ACCOUNT_NOT_EXISTED("305", "该账号不存在"),REPEAT_NAME("306", "该项目名已存在"),REPEAT_SCENE_NAME("307", "该场景名已存在"),REPEAT_ROLE_NAME("308", "该角色名已存在");

    ResultDict(String code, String value) {
        this.value = value;
        this.code = code;
    }

    private String value;
    private String code;

    public String getValue() {
        return value;
    }

    public String getCode() {
        return code;
    }

}
