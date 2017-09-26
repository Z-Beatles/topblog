package cn.waynechu.topblog.service;

import cn.waynechu.topblog.dao.UserDao;
import cn.waynechu.topblog.entity.UserEntity;
import cn.waynechu.topblog.util.RegexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService {

    @Autowired
    private UserDao userDao;

    public UserEntity getUserByAccount(String account) {
        UserEntity user = new UserEntity();
        if (RegexUtil.matchMobile(account)) {
            user.setMobile(account);
            user = userDao.selectOne(user);
        } else if (RegexUtil.matchEmail(account)) {
            user.setEmail(account);
            user = userDao.selectOne(user);
        } else {
            user.setUsername(account);
            user = userDao.selectOne(user);
        }
        return user;
    }
    public UserEntity getUserByLoginId(Long id){
        UserEntity user = new UserEntity();
        user.setId(id);
        return userDao.selectOne(user);
    }
    public List<UserEntity> getUser(UserEntity user){
     return userDao.select(user);
    }
}
