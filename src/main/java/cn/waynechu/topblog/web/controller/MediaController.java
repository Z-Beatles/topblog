package cn.waynechu.topblog.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cn.waynechu.topblog.base.BaseController;
import cn.waynechu.topblog.dto.PageParam;

@Controller
@RequestMapping(value = "/media")
public class MediaController extends BaseController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(PageParam pageParam) {
        return "admin/media/list";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String upload(PageParam pageParam) {
        return "admin/media/upload";
    }
}
