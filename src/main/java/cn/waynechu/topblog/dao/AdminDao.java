package cn.waynechu.topblog.dao;

import cn.waynechu.topblog.entity.AdminEntity;

public interface AdminDao {

    public AdminEntity getByMobile(String account);

    public AdminEntity getByEmail(String account);

    public AdminEntity getByUsername(String account);

    public AdminEntity getByLoginId(Long id);

}
