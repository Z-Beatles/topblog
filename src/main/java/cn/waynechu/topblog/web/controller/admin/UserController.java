package cn.waynechu.topblog.web.controller.admin;

import cn.waynechu.topblog.entity.UserEntity;
import cn.waynechu.topblog.service.UserService;
import cn.waynechu.topblog.util.WebUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cn.waynechu.topblog.base.BaseController;
import cn.waynechu.topblog.model.PageParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;

@Controller
@RequestMapping(value="/admin/user")
public class UserController extends BaseController{

    @Resource
    private UserService userService;

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public String list(PageParam pageParam) {
        return "admin/user/list";
    }
    @ResponseBody
    @RequestMapping("list.json")
    public HashMap<String,Object> getUser(UserEntity user){
        HashMap<String, Object> map = new HashMap<>();
        map.put("data",userService.getUser(user));
        WebUtil.dataTable(userService.getUser(user),10, "totalRecords", "totalDisplayRecords");
        return map;
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
