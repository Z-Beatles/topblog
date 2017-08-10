package cn.waynechu.topblog.web;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cn.waynechu.topblog.Constaint;
import cn.waynechu.topblog.entity.AdminEntity;
import cn.waynechu.topblog.entity.LoginUserEntity;
import cn.waynechu.topblog.service.biz.UserBusiness;
import cn.waynechu.topblog.util.WebUtil;

@Component
public class ContextHelper  {
    
    @Autowired
    private UserBusiness userBusiness;
    
	public Boolean isAdminLogin() {
		Long id = (Long) SecurityUtils.getSubject().getPrincipal();
		if (id == null) {
			return false;
		}
		LoginUserEntity loginUser = userBusiness.getLoginUserById(id);
		return loginUser != null && StringUtils.equals(loginUser.getLoginType(), Constaint.LOGINTYPE_ADMIN);
	}
	
    public String adminAvatar(HttpServletRequest request) {
        return WebUtil.withHttpSession(request.getSession(), Constaint.WEBSESSION_ADMINAVATAR, () -> {
            Long id = (Long) SecurityUtils.getSubject().getPrincipal();
            LoginUserEntity loginUserEntity = userBusiness.getLoginUserById(id);
            if(loginUserEntity != null) {
                return loginUserEntity.getAvatar();
            }
            return "/static/image/user.jpg";
        });
    }
    
    public String adminNickname(HttpServletRequest request) {
        return WebUtil.withHttpSession(request.getSession(), Constaint.WEBSESSION_ADMINNICKNAME, () -> {
            Long id = (Long) SecurityUtils.getSubject().getPrincipal();
            AdminEntity user = userBusiness.getAdminByLoginId(id);
            return user != null ? user.getNickname() : ("[错误]");
        });
    }
    
    public String adminRolename(HttpServletRequest request) {
        return WebUtil.withHttpSession(request.getSession(),Constaint.WEBSESSION_ADMINROLENAME, () -> {
            Long id = (Long) SecurityUtils.getSubject().getPrincipal();
            AdminEntity user = userBusiness.getAdminByLoginId(id);
            return user != null ? user.getRoleNameZh() : ("[错误]");
        });
    }
}
