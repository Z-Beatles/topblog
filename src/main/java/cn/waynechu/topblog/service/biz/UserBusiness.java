package cn.waynechu.topblog.service.biz;

import cn.waynechu.topblog.entity.LoginUserEntity;
import cn.waynechu.topblog.entity.UserEntity;
import cn.waynechu.topblog.service.LoginUserService;
import cn.waynechu.topblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserBusiness {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginUserService loginUserService;

    public LoginUserEntity getLoginUserByAccount(String account) {
        UserEntity user = null;
        try {
            user = userService.getUserByAccount(account);
            if (user == null) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginUserService.getLoginUserByUsername(user.getUsername());
    }

    public UserEntity getUserByLoginId(Long id) {
        return userService.getUserByLoginId(id);
    }

}
