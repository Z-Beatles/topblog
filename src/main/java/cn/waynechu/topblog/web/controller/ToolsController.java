package cn.waynechu.topblog.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cn.waynechu.topblog.base.BaseController;
import cn.waynechu.topblog.dto.PageParam;

@Controller
@RequestMapping(value="/tools")
public class ToolsController extends BaseController{
    
    @RequestMapping(value="/export", method = RequestMethod.GET)
    public String export(PageParam pageParam) {
        return "admin/tools/export";
    }
    
    @RequestMapping(value="/import", method = RequestMethod.GET)
    public String importData(PageParam pageParam) {
        return "admin/tools/import";
    }
}
