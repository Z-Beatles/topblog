package cn.waynechu.topblog.dao;

import java.util.List;

import cn.waynechu.topblog.entity.BaseEntity;
import org.apache.ibatis.annotations.Param;

import cn.waynechu.topblog.entity.LoginUserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginUserDao extends BaseDao<LoginUserEntity>{

    LoginUserEntity getLoginUserByUsername(@Param("username") String username);

    LoginUserEntity getByLoginId(Long id);

}
