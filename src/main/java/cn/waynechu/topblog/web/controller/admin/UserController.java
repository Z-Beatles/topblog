package cn.waynechu.topblog.web.controller.admin;

import cn.waynechu.topblog.Constaint;
import cn.waynechu.topblog.entity.UserEntity;
import cn.waynechu.topblog.model.DataTableParam;
import cn.waynechu.topblog.service.UserService;
import cn.waynechu.topblog.util.WebUtil;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cn.waynechu.topblog.base.BaseController;
import cn.waynechu.topblog.model.PageParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value="/admin/user")
public class UserController extends BaseController{

    @Resource
    private UserService userService;

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public String list(PageParam pageParam) {
        return "admin/user/allUserList";
    }
    @ResponseBody
    @RequestMapping("list.json")
    public HashMap<String,Object> getUser(UserEntity user, DataTableParam dataTableParam){
        return userService.getUser(user,dataTableParam);
    }


    @RequestMapping(value="/editUserPage", method = RequestMethod.GET)
    public String addUserPage(String flag,String id) {
        return "admin/user/editUser";
    }

    @ResponseBody
    @RequestMapping("/addUser")
    public Integer addUser(UserEntity user) {
        return userService.addUser(user);
    }


    @ResponseBody
    @RequestMapping("/deleteUser")
    public Map<String, Object> deleteUser(String id){
        UserEntity user = new UserEntity();
        user.setId(Long.parseLong(id));
        userService.deleteUser(user);
        return WebUtil.success(Constaint.DELETE_SUCCESS);
    }

    @ResponseBody
    @RequestMapping("/updateUser")
    public Integer editUser(UserEntity user){
        return userService.updateUser(user);
    }


    @RequestMapping(value="/editMyselfPage", method = RequestMethod.GET)
    public String editMyselfPage(String id) {
        return "admin/user/myselfInfo";
    }

}
