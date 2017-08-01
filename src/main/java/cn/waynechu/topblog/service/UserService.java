package cn.waynechu.topblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.waynechu.topblog.dao.UserDao;
import cn.waynechu.topblog.entity.UserEntity;
import cn.waynechu.topblog.util.RegexUtil;

@Service
public class UserService {
    
    @Autowired
    private UserDao userDao;

    public UserEntity getByAccount(String account) {
        UserEntity userEntity = null;
        if (RegexUtil.matchMobile(account)) {
            userEntity = userDao.getByMobile(account);
        } else if (RegexUtil.matchEmail(account)) {
            userEntity = userDao.getByEmail(account);
        } else {
            userEntity = userDao.getByUsername(account);
        }
        return userEntity;
    }

}
