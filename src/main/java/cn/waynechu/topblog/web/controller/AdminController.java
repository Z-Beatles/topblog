package cn.waynechu.topblog.web.controller;

import java.io.IOException;
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
import cn.waynechu.topblog.base.BaseController;
import cn.waynechu.topblog.shiro.token.LoginAuthenticationToken;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class AdminController extends BaseController{

    @RequestMapping(method = RequestMethod.GET)
    public String main() {
        return "admin/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "admin/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginAction(HttpServletRequest request, Model model, String loginType, String username, String password, boolean rememberMe)
            throws IOException {
        Subject currentUser = SecurityUtils.getSubject();




        if (!currentUser.isAuthenticated()) {
            LoginAuthenticationToken token = new LoginAuthenticationToken(loginType, username, password, rememberMe,
                    null);
            token.setRememberMe(rememberMe);
            try {
                currentUser.login(token);
                request.getSession().setAttribute("userCountNum",username);
                return "redirect:/";
            } catch (UnknownAccountException e) {
                logger.warn("--->该用户不存在", e);
                request.getSession().removeAttribute("userCountNum");
                request.getSession().setAttribute("errormsg","用户不存在，请重新输入！");
            } catch (LockedAccountException e) {
                logger.warn("--->该用户被锁定", e);
                request.getSession().setAttribute("errormsg","用户被锁定！");
                logger.warn("--->该用户已禁用", e);
                request.getSession().setAttribute("errormsg","该用户已禁用！");
            } catch (IncorrectCredentialsException e) {
                logger.warn("--->密码错误", e);
                request.getSession().setAttribute("errormsg","密码错误，请重新输入密码！");
            } catch (AuthenticationException e) {
                logger.warn("--->系统错误", e);
                request.getSession().setAttribute("errormsg","系统异常！");
            }
        }
        return "redirect:admin/login";
    }

    @RequestMapping(value = "/unauthorized", method = RequestMethod.GET)
    public String unauthorized() {
        return "/unauthorized";
    }

}