package cn.waynechu.topblog.web.controller.admin;

import cn.waynechu.topblog.Constaint;
import cn.waynechu.topblog.entity.UserEntity;
import cn.waynechu.topblog.model.DataTableParam;
import cn.waynechu.topblog.service.LoginUserService;
import cn.waynechu.topblog.service.UserService;
import cn.waynechu.topblog.util.WebUtil;
import cn.waynechu.topblog.vo.MyResult;
import cn.waynechu.topblog.vo.UserVo;
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
    @Resource
    private LoginUserService loginUserService;

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public String list(PageParam pageParam) {
        return "admin/user/allUserList";
    }
    @ResponseBody
    @RequestMapping("list.json")
    public HashMap<String,Object> getUser(UserEntity user, DataTableParam dataTableParam){

        HashMap<String, Object> users = null;
        try {
            users = userService.getUser(user, dataTableParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }


    @RequestMapping(value="/editUserPage", method = RequestMethod.GET)
    public String addUserPage(String flag,String id) {
        return "admin/user/editUser";
    }

    @ResponseBody
    @RequestMapping("/addUser")
    public Integer addUser(UserEntity user) {
        Integer result = null;
        try {
            result = userService.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    @ResponseBody
    @RequestMapping("/deleteUser")
    public Map<String, Object> deleteUser(String id){
        try {
            UserEntity user = new UserEntity();
            user.setId(Long.parseLong(id));
            userService.deleteUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WebUtil.success(Constaint.DELETE_SUCCESS);
    }

    @ResponseBody
    @RequestMapping("/updateUser")
    public Integer editUser(UserEntity user){
        Integer result = null ;
        try {
            result = userService.updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    @RequestMapping(value="/editMyselfPage", method = RequestMethod.GET)
    public String editMyselfPage(String id) {
        return "admin/user/myselfInfo";
    }


    /**
    　* @Description: 注册用户
    　* @param
    　* @return
    　* @throws
    　* @author xiacunhai
    　* @date 2018/8/15 16:35
    　*/
    @RequestMapping(value="/registerUser", method = RequestMethod.POST)
    public String registerUser(UserVo vo) {
        System.out.println(vo);
        MyResult myResult = loginUserService.registerUser(vo);

        return "admin/login";
    }



}
