package cn.waynechu.topblog.service;

import cn.waynechu.topblog.dao.PermissionDao;
import cn.waynechu.topblog.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AuthorizationService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    public List<String> getRolesByLoginUserId(Long id) {
        return roleDao.getRolesByLoginUserId(id);
    }

    public List<String> getPermissionsByRole(String role) {
        return permissionDao.getPermissionsByRole(role);
    }

}
