package cn.waynechu.topblog.web.controller.admin;

import cn.waynechu.topblog.entity.UserEntity;
import cn.waynechu.topblog.model.DataTableParam;
import cn.waynechu.topblog.service.UserService;
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
    public HashMap<String,Object> getUser(UserEntity user, DataTableParam dataTableParam){
        return userService.getUser(user,dataTableParam);
    }

    @ResponseBody
    @RequestMapping("/addUser")
    public Integer addUser(UserEntity user) {
        return userService.addUser(user);
    }


    @ResponseBody
    @RequestMapping("/removeUser")
    public Integer removeUser(UserEntity user){
        return userService.deleteUser(user);
    }

    @ResponseBody
    @RequestMapping("/editUser")
    public Integer editUser(UserEntity user){
        return userService.updateUser(user);
    }
}
