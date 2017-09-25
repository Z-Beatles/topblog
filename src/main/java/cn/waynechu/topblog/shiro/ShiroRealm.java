package cn.waynechu.topblog.shiro;

import cn.waynechu.topblog.entity.LoginUserEntity;
import cn.waynechu.topblog.entity.UserEntity;
import cn.waynechu.topblog.service.AuthorizationService;
import cn.waynechu.topblog.service.biz.UserBusiness;
import cn.waynechu.topblog.shiro.token.LoginAuthenticationToken;
import cn.waynechu.topblog.util.MapUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@SuppressWarnings("unchecked")
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserBusiness userBusiness;

    @Autowired
    private AuthorizationService authorizationService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        LoginAuthenticationToken myToken = (LoginAuthenticationToken) token;
        LoginUserEntity loginUser = userBusiness.getLoginUserByAccount(myToken.getUsername());

        if (loginUser == null) {
            throw new UnknownAccountException("用户[" + myToken.getLoginType() + "," + myToken.getUsername() + "]不存在");
        } else if (Boolean.TRUE.equals(loginUser.getLocked())) {
            throw new LockedAccountException("用户[" + myToken.getLoginType() + "," + myToken.getUsername() + "]已锁定");
        } else if (Boolean.TRUE.equals(loginUser.getDisabled())) {
            throw new DisabledAccountException("用户[" + myToken.getLoginType() + "," + myToken.getUsername() + "]已禁用");
        }

        UserEntity userEntity = userBusiness.getUserByLoginId(loginUser.getId());
        Map<String, Object> data = MapUtil.asMap("id", userEntity.getId(), "loginId", loginUser.getId(), "loginType", loginUser.getLoginType(), "username", loginUser.getUsername(), "nickname", userEntity.getNickname(), "avatar", loginUser.getAvatar(), "mobile", userEntity.getMobile(), "email", userEntity.getEmail());
        return new SimpleAuthenticationInfo(data, loginUser.getPasswordHash(), ByteSource.Util.bytes(loginUser.getPasswordSalt()), getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Map<String, Object> principal = (Map<String, Object>) principals.getPrimaryPrincipal();
        Long id = (Long) principal.get("loginId");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<String> roles = authorizationService.getRolesByLoginUserId(id);
        List<String> permissions = new ArrayList<>();
        for (String role : roles) {
            permissions.addAll(authorizationService.getPermissionsByRole(role));
        }
        info.addRoles(roles);
        info.addStringPermissions(permissions);
        return info;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof LoginAuthenticationToken || super.supports(token);
    }
}
