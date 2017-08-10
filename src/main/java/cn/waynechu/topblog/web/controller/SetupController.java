package cn.waynechu.topblog.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cn.waynechu.topblog.base.BaseController;
import cn.waynechu.topblog.dto.PageParam;

@Controller
@RequestMapping(value="/setup")
public class SetupController extends BaseController{
    
    @RequestMapping(value="/discussion", method = RequestMethod.GET)
    public String discussion(PageParam pageParam) {
        return "admin/setup/discussion";
    }
    
    @RequestMapping(value="/media", method = RequestMethod.GET)
    public String media(PageParam pageParam) {
        return "admin/setup/media";
    }
    
    @RequestMapping(value="/normal", method = RequestMethod.GET)
    public String normal(PageParam pageParam) {
        return "admin/setup/normal";
    }
    @RequestMapping(value="/reading", method = RequestMethod.GET)
    public String reading(PageParam pageParam) {
        return "admin/setup/reading";
    }
    @RequestMapping(value="/writing", method = RequestMethod.GET)
    public String writing(PageParam pageParam) {
        return "admin/setup/writing";
    }
}
