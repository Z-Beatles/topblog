package cn.waynechu.topblog.dao;

import cn.waynechu.topblog.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

import javax.jws.soap.SOAPBinding;
import javax.swing.text.html.parser.Entity;
import java.util.List;

public interface UserDao extends BaseDao<UserEntity>{
    UserEntity selectOne(UserEntity user);
}
