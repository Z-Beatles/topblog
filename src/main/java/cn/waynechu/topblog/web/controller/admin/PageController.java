package cn.waynechu.topblog.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cn.waynechu.topblog.base.BaseController;
import cn.waynechu.topblog.model.PageParam;

@Controller
@RequestMapping(value="/admin/page")
public class PageController extends BaseController{
    
    @RequestMapping(value="/list", method = RequestMethod.GET)
    public String list(PageParam pageParam) {
        return "admin/page/list";
    }
    
    @RequestMapping(value="/new", method = RequestMethod.GET)
    public String newPage(PageParam pageParam) {
        return "admin/page/new";
    }
}
