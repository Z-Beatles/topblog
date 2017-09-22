package cn.waynechu.topblog.service;

import cn.waynechu.topblog.dao.UserDao;
import cn.waynechu.topblog.entity.UserEntity;
import cn.waynechu.topblog.util.RegexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService {

    @Autowired
    private UserDao userDao;

    public UserEntity getUserByAccount(String account) {
        UserEntity userEntity = null;
        if (RegexUtil.matchMobile(account)) {
            userEntity = userDao.getUserByMobile(account);
        } else if (RegexUtil.matchEmail(account)) {
            userEntity = userDao.getUserByEmail(account);
        } else {
            userEntity = userDao.getUserByUsername(account);
        }
        return userEntity;
    }

    public UserEntity getUserByLoginId(Long id) {
        return userDao.getUserByLoginId(id);
    }

}
