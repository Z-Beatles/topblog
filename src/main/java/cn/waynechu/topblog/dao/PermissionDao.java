package cn.waynechu.topblog.dao;

import java.util.List;

public interface PermissionDao {

    List<String> getPermissionsByRole(String role);

}
