package cn.waynechu.topblog.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cn.waynechu.topblog.base.BaseController;
import cn.waynechu.topblog.dto.PageParam;

@Controller
@RequestMapping(value="/admin/article")
public class ArticleController extends BaseController{
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(PageParam pageParam) {
        return "admin/article/list";
    }
    
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newArticle(PageParam pageParam) {
        return "admin/article/new";
    }
}
