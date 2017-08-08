package cn.waynechu.topblog.service;

import java.util.List;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import cn.waynechu.topblog.dao.LoginUserDao;
import cn.waynechu.topblog.entity.LoginUserEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class LoginUserServiceTest {

    @Resource
    private LoginUserDao loginUserDao;

    @Test
    public void getByLoginTypeAndUsername() throws Exception {
        String loginType = "user";
        String username = "zhuwei";
        LoginUserEntity loginUserEntity = loginUserDao.getByLoginTypeAndUsername(loginType, username);
        System.out.println(loginUserEntity);
    }

    @Test
    public void getByLoginId() throws Exception {
        Long id = 1L;
        LoginUserEntity loginUserEntity = loginUserDao.getByLoginId(id);
        System.out.println(loginUserEntity);
    }

    @Test
    public void getPermissionsForLoginUserId() throws Exception {
        Long id = 2L;
        List<String> permissions = loginUserDao.getPermissionsForLoginUserId(id);
        System.out.println("权限列表:" + permissions);
    }
}
