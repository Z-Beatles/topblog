package cn.waynechu.topblog.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cn.waynechu.topblog.base.BaseController;
import cn.waynechu.topblog.dto.PageParam;

@Controller
@RequestMapping(value="/comment")
public class CommentController extends BaseController{
    
    @RequestMapping(method = RequestMethod.GET)
    public String comment(PageParam pageParam) {
        return "admin/comment/comment";
    }
}
