package cn.waynechu.topblog.service;

import cn.waynechu.topblog.dao.LoginUserDao;
import cn.waynechu.topblog.dao.UserDao;
import cn.waynechu.topblog.entity.LoginUserEntity;
import cn.waynechu.topblog.entity.UserEntity;
import cn.waynechu.topblog.model.DataTableParam;
import cn.waynechu.topblog.util.RegexUtil;
import cn.waynechu.topblog.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private LoginUserDao loginUserDao;

    public UserEntity getUserByAccount(String account) {
        UserEntity user = new UserEntity();
        if (RegexUtil.matchMobile(account)) {
            user.setMobile(account);
            user = userDao.selectOne(user);
        } else if (RegexUtil.matchEmail(account)) {
            user.setEmail(account);
            user = userDao.selectOne(user);
        } else {
            user.setUsername(account);
            user = userDao.selectOne(user);
        }
        return user;
    }
    public UserEntity getUserByLoginId(Long id){
        UserEntity user = new UserEntity();
        user.setId(id);
        return userDao.selectOne(user);
    }
    //查询通用
    public HashMap<String,Object> getUser(UserEntity user , DataTableParam dataTableParam){
        HashMap<String, Object> map = new HashMap<String, Object>();
        user.setPageSize(dataTableParam.getLength());//页面大小
        user.setPageIndex((dataTableParam.getDraw()-1)*dataTableParam.getLength());//起始条-1
        Integer userCount = userDao.count(user);//查询到的记录数
        List<UserEntity> users = userDao.select(user);//查询的到的数据
        //封装展示对象信息
        List<UserVo> userVos = new ArrayList<>();
        for (UserEntity u : users) {
            LoginUserEntity loginUser = loginUserDao.getLoginUserByUsername(u.getUsername());
            UserVo userVo = new UserVo();
            userVo.setId(loginUser.getId());
            userVo.setAvatar(loginUser.getAvatar());
            userVo.setUsername(loginUser.getUsername());
            userVo.setNickname(u.getNickname());
            userVo.setMobile(u.getMobile());
            userVo.setEmail(u.getEmail());
            userVo.setDisabled(loginUser.getDisabled());
            userVo.setLocked(loginUser.getLocked());
            userVos.add(userVo);
        }

        map.put("data",userVos);
        map.put("recordsTotal",userCount) ;
        map.put("recordsFiltered",userCount);
        return map;
    }
    //增删改
    public  Integer addUser(UserEntity user){
        return userDao.insert(user);
    }
    public Integer deleteUser(UserEntity user){
        LoginUserEntity loginUser= new LoginUserEntity();
        loginUser.setId(user.getId());
        loginUserDao.delete(loginUser);
        return userDao.delete(user);
    };
    public Integer updateUser(UserEntity user){
        return userDao.update(user);
    }
}
