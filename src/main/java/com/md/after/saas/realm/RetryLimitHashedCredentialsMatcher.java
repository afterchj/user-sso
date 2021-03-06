package com.md.after.saas.realm;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;


/**
 * Created by hongjian.chen on 2019/4/9.
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    private static final String ALGORITHM = "SHA-1";

    public RetryLimitHashedCredentialsMatcher() {
        super(ALGORITHM);
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
        EasyTypeToken tk = (EasyTypeToken) authcToken;
        //如果是免密登录直接返回true
        if (tk.getType().equals(LoginType.NOPASSWD)) {
            return true;
        }
        //不是免密登录，调用父类的方法
        return super.doCredentialsMatch(tk, info);
    }
}
