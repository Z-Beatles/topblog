package cn.waynechu.topblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.waynechu.topblog.dao.AdminDao;
import cn.waynechu.topblog.entity.AdminEntity;
import cn.waynechu.topblog.util.RegexUtil;

@Service
public class AdminService {
    
    @Autowired
    private AdminDao adminDao;

    public AdminEntity getByAccount(String account) {
        AdminEntity adminEntity = null;
        if (RegexUtil.matchMobile(account)) {
            adminEntity = adminDao.getByMobile(account);
        } else if (RegexUtil.matchEmail(account)) {
            adminEntity = adminDao.getByEmail(account);
        } else {
            adminEntity = adminDao.getByUsername(account);
        }
        return adminEntity;
    }

    public AdminEntity getByLoginId(Long id) {
        AdminEntity adminEntity = adminDao.getByLoginId(id);
        return adminEntity;
    }

}
