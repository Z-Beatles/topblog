package cn.waynechu.topblog.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cn.waynechu.topblog.base.BaseController;
import cn.waynechu.topblog.model.PageParam;

@Controller
@RequestMapping(value="/admin/user")
public class UserController extends BaseController{
    
    @RequestMapping(value="/list", method = RequestMethod.GET)
    public String list(PageParam pageParam) {
        return "admin/user/list";
    }
    
    @RequestMapping(value="/add", method = RequestMethod.GET)
    public String add(PageParam pageParam) {
        return "admin/user/add";
    }
    
    @RequestMapping(value="/info", method = RequestMethod.GET)
    public String info(PageParam pageParam) {
        return "admin/user/info";
    }
    
}
