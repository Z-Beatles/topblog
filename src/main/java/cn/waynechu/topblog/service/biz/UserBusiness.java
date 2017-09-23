package cn.waynechu.topblog.service.biz;

import cn.waynechu.topblog.entity.LoginUserEntity;
import cn.waynechu.topblog.entity.UserEntity;
import cn.waynechu.topblog.service.LoginUserService;
import cn.waynechu.topblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserBusiness {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginUserService loginUserService;

    public LoginUserEntity getLoginUserByAccount(String account) {
        UserEntity user = userService.getUserByAccount(account);
        if (user == null) {
            return null;
        }
        return loginUserService.getLoginUserByUsername(user.getUsername());
    }

    public UserEntity getUserByLoginId(Long id) {
        return userService.getUserByLoginId(id);
    }

}
