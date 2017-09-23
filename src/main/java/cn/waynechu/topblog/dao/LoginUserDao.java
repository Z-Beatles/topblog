package cn.waynechu.topblog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.waynechu.topblog.entity.LoginUserEntity;

public interface LoginUserDao {

    LoginUserEntity getLoginUserByUsername(@Param("username") String username);

    LoginUserEntity getByLoginId(Long id);

}
