package cn.waynechu.topblog.web;

import cn.waynechu.topblog.dao.CategoryDao;
import cn.waynechu.topblog.service.AuthorizationService;
import cn.waynechu.topblog.service.biz.UserBusiness;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Component
@SuppressWarnings("unchecked")
public class ContextHelper {

    @Autowired
    private UserBusiness userBusiness;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private AuthorizationService authorizationService;


    public Boolean isUserLogin() {
        Map<String, Object> principal = (Map<String, Object>) SecurityUtils.getSubject().getPrincipal();
        return principal != null;
    }

    public String userAvatar(HttpServletRequest request) {
        Map<String, Object> principal = (Map<String, Object>) SecurityUtils.getSubject().getPrincipal();
        String avatar = (String) principal.get("avatar");
        return avatar != null ? avatar : "/static/image/user.jpg";
    }

    public String userNickname(HttpServletRequest request) {
        Map<String, Object> principal = (Map<String, Object>) SecurityUtils.getSubject().getPrincipal();
        String nickname = (String) principal.get("nickname");
        return nickname != null ? nickname : "[未知用户]";
    }

    public String userRolename(HttpServletRequest request) {
        Map<String, Object> principal = (Map<String, Object>) SecurityUtils.getSubject().getPrincipal();
        Long id = (Long) principal.get("loginId");
        List<String> roles = authorizationService.getRolesByLoginUserId(id);
        return roles != null ? roles.get(0) : ("[未知角色]");
    }

}
