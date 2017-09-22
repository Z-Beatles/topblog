package cn.waynechu.topblog.dao;

import java.util.List;

public interface RoleDao {

    List<String> getRolesByLoginUserId(Long id);

}