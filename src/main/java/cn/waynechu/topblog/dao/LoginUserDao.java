package cn.waynechu.topblog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.waynechu.topblog.entity.LoginUserEntity;

public interface LoginUserDao {

    public LoginUserEntity getByLoginTypeAndUsername(@Param("loginType")String loginType, @Param("username")String username);

    public LoginUserEntity getByLoginId(Long id);

    public List<String> getPermissionsForLoginUserId(Long id);

}
