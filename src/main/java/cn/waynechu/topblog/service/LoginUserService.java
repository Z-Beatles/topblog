package cn.waynechu.topblog.service;

import cn.waynechu.topblog.dao.LoginUserDao;
import cn.waynechu.topblog.entity.LoginUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LoginUserService {

    @Autowired
    private LoginUserDao loginUserDao;

    public LoginUserEntity getLoginUserByUsername(String username) {
        return loginUserDao.getLoginUserByUsername(username);
    }

    public LoginUserEntity getByLoginId(Long id) {
        return loginUserDao.getByLoginId(id);
    }

}
