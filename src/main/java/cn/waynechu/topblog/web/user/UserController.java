package cn.waynechu.topblog.web.user;

import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import cn.waynechu.topblog.base.BaseController;
import cn.waynechu.topblog.shiro.token.LoginAuthenticationToken;
import cn.waynechu.topblog.util.WebUtil;

@Controller
@RequestMapping
public class UserController extends BaseController{

    @RequestMapping(method = RequestMethod.GET)
    public String mian() {
        return "app/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(WebRequest request) {
        String requestURI = WebUtil.getShiroRequestURI(request);
        if (requestURI != null) {
            if (StringUtils.equalsIgnoreCase(requestURI, "/admin") || StringUtils.startsWith(requestURI, "/admin/")) {
                return "redirect:/admin/login";
            }
        }
        return "app/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginAction(WebRequest request, Model model, String loginType, String username, String password,
            boolean rememberMe) throws IOException {
        Subject currentUser = SecurityUtils.getSubject();

        if (!currentUser.isAuthenticated()) {
            LoginAuthenticationToken token = new LoginAuthenticationToken(loginType, username, password, rememberMe,
                    null);
            token.setRememberMe(true);
            try {
                currentUser.login(token);
                return "redirect:/";
            } catch (UnknownAccountException e) {
                logger.warn("该用户不存在", e);
                model.addAttribute("errormsg", "该用户不存在");
            } catch (LockedAccountException e) {
                logger.warn("该用户被锁定", e);
                model.addAttribute("errormsg", "该用户被锁定");
            } catch (DisabledAccountException e) {
                logger.warn("该用户已禁用", e);
                model.addAttribute("errormsg", "该用户已禁用");
            } catch (IncorrectCredentialsException e) {
                logger.warn("密码错误", e);
                model.addAttribute("errormsg", "密码错误");
            } catch (AuthenticationException e) {
                logger.warn("系统错误", e);
                model.addAttribute("errormsg", "系统错误");
            }
        }
        return "app/login";
    }

    @RequestMapping(value = "/unauthorized", method = RequestMethod.GET)
    public String unauthorized(Model model) {
        return "unauthorized";
    }

}
