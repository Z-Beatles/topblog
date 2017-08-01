package cn.waynechu.topblog.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class SecondRealm extends AuthorizingRealm  {

    // 用于认证Authentication的方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("---> [SHA1Realm] doGetAuthenticationInfo");
        
        //1. 把 AuthenticationToken 转化为 UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;

        //2. 从 UsernamePasswordToken 中获取 username
        String username = upToken.getUsername();

        //3. 从数据库中查询 username 对应的用户记录
        System.out.println("从数据库中获取  username：" + username + " 对应的信息");

        //4. 若用户不存在，则抛出 UnknownAccountException 异常
        if ("unknown".equals(username)) {
            throw new UnknownAccountException("用户不存在！！");
        }

        //5. 根据用户的情况，抛出其他的 AuthenticationException 异常
        if ("monster".equals(username)) {
            throw new LockedAccountException("用户被锁定!!");
        }

        //6. 构建 SimpleAuthenticationInfo 对象并返回
        // 以下信息从数据库中获取
        // 1). principal: 认证的实体信息，可以是username，也可以是数据表对应的用户实体类对象
        Object principal = username;
        
        // 2). credentials: 密码
        Object hashedCredentials = null;
        if("admin".equals(username)) {
            hashedCredentials = "55516a952f026f086ffc38f73348b34e58e97756";
        }else if("user".equals(username)) {
            hashedCredentials = "700df66f8a8ebc7506f4dd0321e2bbbc76dbd80c";
        }
        
        // 3). realName: 当前 realm 对象的name，调用父类的 getName() 方法即可
        String realmName = getName();
        // 盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);
        // SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, realmName);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, hashedCredentials, credentialsSalt, realmName);
        return info;
    }
    
    // 用于授权Authorization的方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("---> [SHA1Realm] doGetAuthenticationInfo 验证授权");
        
        //1. 从 PrincipalCollection 中获取登录用户的信息
        Object principal = principals.getPrimaryPrincipal();
        
        //2. 利用登录的用户信息 来获取当前用户的角色或权限（可能需要查询数据库）
        Set<String> roles = new HashSet<>();
        roles.add("user");
        if("admin".equals(principal)) {
            roles.add("admin");
        }
        
        //3. 创建 SimpleAuthorizationInfo 并设置 roles 属性
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
         
        //4. 返回 SimpleAuthorizationInfo 对象
        return info;
    }
    
//  // 利用登录的用户信息 来获取当前用户的 角色 或权限
//  @Override
//  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//      Long id = (Long) principals.getPrimaryPrincipal();
//      LoginUserEntity user = userBusiness.getLoginUserById(id);
//      Set<String> roles = new HashSet<String>(userBusiness.getPermissionsForLoginUser(user));
//      SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
//      return info;
//  }
    
    public static void main(String[] args) {
        String algorithmName = "SHA-1";
        Object source = "123456";
        ByteSource salt = ByteSource.Util.bytes("c4516b44f03e5fee498d90f131780870");
        int hashIterations = 1024;
        Object result = new SimpleHash(algorithmName, source, salt, hashIterations);
        System.out.println(result);
    }

}
