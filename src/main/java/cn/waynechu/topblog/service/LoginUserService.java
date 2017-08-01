package cn.waynechu.topblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.waynechu.topblog.dao.LoginUserDao;
import cn.waynechu.topblog.entity.LoginUserEntity;

@Service
public class LoginUserService {
    
    @Autowired
    private LoginUserDao loginUserDao;

    public LoginUserEntity getByLoginTypeAndUsername(String loginType, String username) {
        return loginUserDao.getByLoginTypeAndUsername(loginType, username);
    }

    public LoginUserEntity getByLoginId(Long id) {
        return loginUserDao.getByLoginId(id);
    }

    public List<String> getPermissionsForLoginUserId(Long id) {
        
        return loginUserDao.getPermissionsForLoginUserId(id);
    }
   
}
