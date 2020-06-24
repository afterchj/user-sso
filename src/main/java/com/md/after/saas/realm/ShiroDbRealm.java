package com.md.after.saas.realm;

import com.md.after.saas.dao.UserExtendDao;
import com.md.after.saas.entity.User;
import com.md.after.saas.utils.Digests;
import com.md.after.saas.utils.Encodes;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ShiroDbRealm extends AuthorizingRealm {

    private static final int INTERATIONS = 1024;
    private static final int SALT_SIZE = 8;
    private static final String ALGORITHM = "SHA-1";

    @Autowired
    private UserExtendDao userExtendDao;


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 登录之后用于授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(new HashSet<>(userExtendDao.getRoles(username)));
        List permissions = userExtendDao.getPermissions(username);
//        logger.warn("permission {}", permissions);
        authorizationInfo.setStringPermissions(new HashSet<>(permissions));
        return authorizationInfo;
    }

    /**
     * 用于验证身份
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        String username = (String) token.getPrincipal();
        User user = userExtendDao.selectByUsername(username);
        logger.warn("doGetAuthenticationInfo {}", user);

        AuthenticationInfo info = null;
        if (null != user) {
            if (user.getStatus() == 0) {
                throw new DisabledAccountException("该账号已禁用！");
            } else if (user.isLocked() == 1) {
                throw new LockedAccountException("该账号在别处登入！");
            }
        } else {
            throw new UnknownAccountException("登录名错误");
        }
        try {
            byte[] salt = Encodes.decodeHex(user.getSalt());
            info = new SimpleAuthenticationInfo(username, user.getPwd(), ByteSource.Util.bytes(salt), getName());
        } catch (Exception e) {
            throw new IncorrectCredentialsException("账号密码不正确！");
        }
        return info;
    }

    @PostConstruct
    public void initCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new RetryLimitHashedCredentialsMatcher();
        matcher.setHashIterations(INTERATIONS);
        setCredentialsMatcher(matcher);
    }

    public HashPassword encrypt(String plainText) {
        HashPassword result = new HashPassword();
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        result.salt = Encodes.encodeHex(salt);
        byte[] hashPassword = Digests.sha1(plainText.getBytes(), salt, INTERATIONS);
        result.password = Encodes.encodeHex(hashPassword);
        return result;
    }

    public static class HashPassword {
        public String salt;
        public String password;
    }

}