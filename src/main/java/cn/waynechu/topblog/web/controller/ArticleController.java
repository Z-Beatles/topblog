package cn.waynechu.topblog.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cn.waynechu.topblog.base.BaseController;
import cn.waynechu.topblog.dto.PageParam;

@Controller
@RequestMapping(value="/article")
public class ArticleController extends BaseController{
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(PageParam pageParam) {
        return "admin/article/list";
    }
    
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newArticle(PageParam pageParam) {
        return "admin/article/new";
    }
    
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public String category(PageParam pageParam) {
        return "admin/article/category";
    }
    
    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public String tags(PageParam pageParam) {
        return "admin/article/tags";
    }
    
}
