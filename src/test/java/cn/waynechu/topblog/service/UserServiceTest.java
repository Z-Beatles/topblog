package cn.waynechu.topblog.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.waynechu.topblog.dao.UserDao;
import cn.waynechu.topblog.entity.UserEntity;
import cn.waynechu.topblog.util.RegexUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class UserServiceTest {

    @Resource
    private UserDao userDao;

    @Test
    public void getByAccount() throws Exception {
        String account = "zhuwei";
        // String account = "15538107627";
        // String account = "waynechu1996@gmail.com";
        UserEntity userEntity = null;
        if (RegexUtil.matchMobile(account)) {
            userEntity = userDao.getByMobile(account);
        } else if (RegexUtil.matchEmail(account)) {
            userEntity = userDao.getByEmail(account);
        } else {
            userEntity = userDao.getByUsername(account);
        }
        System.out.println(userEntity);
    }
}
