package cn.waynechu.topblog.service.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cn.waynechu.topblog.Constaint;
import cn.waynechu.topblog.entity.AdminEntity;
import cn.waynechu.topblog.entity.LoginUserEntity;
import cn.waynechu.topblog.entity.UserEntity;
import cn.waynechu.topblog.service.AdminService;
import cn.waynechu.topblog.service.LoginUserService;
import cn.waynechu.topblog.service.UserService;

@Component
public class UserBusiness{
    
    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private LoginUserService loginUserService;

    public LoginUserEntity getLoginUserByAccount(String loginType, String account) {
        if (Constaint.LOGINTYPE_USER.equalsIgnoreCase(loginType)) {
            UserEntity user = userService.getByAccount(account);
            if (user == null) {
                return null;
            }
            return loginUserService.getByLoginTypeAndUsername(Constaint.LOGINTYPE_USER, user.getUsername());
        } else if (Constaint.LOGINTYPE_ADMIN.equalsIgnoreCase(loginType)) {
            AdminEntity user = adminService.getByAccount(account);
            if (user == null) {
                return null;
            }
            return loginUserService.getByLoginTypeAndUsername(Constaint.LOGINTYPE_ADMIN, user.getUsername());
        }
        return null;
    }

    public LoginUserEntity getLoginUserById(Long id) {
        return loginUserService.getByLoginId(id);
    }

    public List<String> getPermissionsForLoginUser(LoginUserEntity user) {
        return loginUserService.getPermissionsForLoginUserId(user.getId());
    }

    public AdminEntity getAdminByLoginId(Long id) {
        return adminService.getByLoginId(id);
        
    }
}
