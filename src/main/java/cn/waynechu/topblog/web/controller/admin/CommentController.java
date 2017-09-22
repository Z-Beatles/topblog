package cn.waynechu.topblog.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cn.waynechu.topblog.base.BaseController;
import cn.waynechu.topblog.model.PageParam;

@Controller
@RequestMapping(value = "/admin/comment")
public class CommentController extends BaseController {

    @RequestMapping(method = RequestMethod.GET)
    public String comment(PageParam pageParam) {
        return "admin/comment/list";
    }
}
