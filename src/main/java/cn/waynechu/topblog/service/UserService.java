package cn.waynechu.topblog.service;

import cn.waynechu.topblog.dao.UserDao;
import cn.waynechu.topblog.entity.UserEntity;
import cn.waynechu.topblog.model.DataTableParam;
import cn.waynechu.topblog.util.RegexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService {

    @Autowired
    private UserDao userDao;

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
        map.put("data",userDao.select(user));//查询的到的数据
        map.put("recordsTotal",userCount) ;
        map.put("recordsFiltered",userCount);
        return map;
    }
    //增删改
    public  Integer addUser(UserEntity user){
        return userDao.insert(user);
    }
    public Integer deleteUser(UserEntity user){
        return userDao.delete(user);
    };
    public Integer updateUser(UserEntity user){
        return userDao.update(user);
    }
}
