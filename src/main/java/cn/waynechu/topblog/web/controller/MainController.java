package cn.waynechu.topblog.web.controller;

import cn.waynechu.topblog.base.BaseController;
import cn.waynechu.topblog.shiro.token.LoginAuthenticationToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
@RequestMapping
public class MainController extends BaseController {

    @RequestMapping(method = RequestMethod.GET)
    public String main() {
        return "user/index";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin() {
        return "admin/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "admin/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginAction(Model model, String loginType, String username, String password, boolean rememberMe) throws IOException {
        Subject currentUser = SecurityUtils.getSubject();

        if (!currentUser.isAuthenticated()) {
            LoginAuthenticationToken token = new LoginAuthenticationToken(loginType, username, password, rememberMe, null);
            token.setRememberMe(rememberMe);
            try {
                currentUser.login(token);
                return "redirect:/admin";
            } catch (UnknownAccountException e) {
                logger.warn("--->该用户不存在", e);
                model.addAttribute("errormsg", "抱歉，该用户不存在");
            } catch (LockedAccountException e) {
                logger.warn("--->该用户被锁定", e);
                model.addAttribute("errormsg", "抱歉，该帐号被锁定");
            } catch (DisabledAccountException e) {
                logger.warn("--->该用户已禁用", e);
                model.addAttribute("errormsg", "抱歉，该帐号已禁用");
            } catch (IncorrectCredentialsException e) {
                logger.warn("--->密码错误", e);
                model.addAttribute("errormsg", "密码错误，请重新输入");
            } catch (AuthenticationException e) {
                logger.warn("--->系统错误", e);
                model.addAttribute("errormsg", "系统错误，请稍候再试！");
            }
        }
        return "admin/login";
    }

    @RequestMapping(value = "/unauthorized", method = RequestMethod.GET)
    public String unauthorized() {
        return "/unauthorized";
    }
}
