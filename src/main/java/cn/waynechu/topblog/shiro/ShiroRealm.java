package cn.waynechu.topblog.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.common.collect.Sets;
import cn.waynechu.topblog.entity.LoginUserEntity;
import cn.waynechu.topblog.service.biz.UserBusiness;
import cn.waynechu.topblog.shiro.token.LoginAuthenticationToken;

@Component
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserBusiness userBusiness;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        LoginAuthenticationToken laToken = (LoginAuthenticationToken) token;
        LoginUserEntity user = userBusiness.getLoginUserByAccount(laToken.getLoginType(), laToken.getUsername());

        if (user == null) {
            throw new UnknownAccountException("用户[" + laToken.getLoginType() + "," + laToken.getUsername() + "]不存在");
        } else if (Boolean.TRUE.equals(user.getLocked())) {
            throw new LockedAccountException("用户[" + laToken.getLoginType() + "," + laToken.getUsername() + "]已锁定");
        } else if (Boolean.TRUE.equals(user.getDisabled())) {
            throw new DisabledAccountException("用户[" + laToken.getLoginType() + "," + laToken.getUsername() + "]已禁用");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getId(), user.getPasswordHash(),
                ByteSource.Util.bytes(user.getPasswordSalt()), getName());
        return info;
    }
    
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Long id = (Long) principals.getPrimaryPrincipal();
        LoginUserEntity user = userBusiness.getLoginUserById(id);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(Sets.newHashSet(userBusiness.getPermissionsForLoginUser(user)));
        return info;
    }
    
    @Override
    public boolean supports(AuthenticationToken token) {
        if (token instanceof LoginAuthenticationToken) {
            return true;
        }
        return super.supports(token);
    }
}
