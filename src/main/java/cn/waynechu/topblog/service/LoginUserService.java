package cn.waynechu.topblog.service;

import cn.waynechu.topblog.dao.LoginUserDao;
import cn.waynechu.topblog.entity.LoginUserEntity;
import cn.waynechu.topblog.entity.UserEntity;
import cn.waynechu.topblog.vo.MyResult;
import cn.waynechu.topblog.vo.UserVo;
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

    public MyResult registerUser(UserVo vo){

        //添加用户基本信息
        UserEntity userEntity = new UserEntity();
        //添加用户登录信息
        LoginUserEntity loginUserEntity = new LoginUserEntity();
        //用户权限信息



        return null;
    }



}
