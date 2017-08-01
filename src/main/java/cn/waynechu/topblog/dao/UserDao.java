package cn.waynechu.topblog.dao;

import cn.waynechu.topblog.entity.LoginUserEntity;
import cn.waynechu.topblog.entity.UserEntity;

public interface UserDao {

    public UserEntity getByMobile(String account);

    public UserEntity getByEmail(String account);

    public UserEntity getByUsername(String account);

    public LoginUserEntity getByLoginId(Long id);

}
