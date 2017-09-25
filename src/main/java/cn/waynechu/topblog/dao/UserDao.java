package cn.waynechu.topblog.dao;

import cn.waynechu.topblog.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    UserEntity getUserByMobile(@Param("mobile") String account);

    UserEntity getUserByEmail(@Param("email") String account);

    UserEntity getUserByUsername(@Param("username") String account);

    UserEntity getUserByLoginId(@Param("id") Long id);

}
