package cn.waynechu.topblog.service;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.waynechu.topblog.entity.AdminEntity;
import cn.waynechu.topblog.util.RegexUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class AdminServiceTest {

    @Resource
    private AdminDao adminDao;

    @Test
    public void getByAccount() throws Exception {
        String account = "zhuwei";
        // String account = "15538107627";
        // String account = "waynechu1996@gmail.com";
        AdminEntity adminEntity = null;
        if (RegexUtil.matchMobile(account)) {
            adminEntity = adminDao.getByMobile(account);
        } else if (RegexUtil.matchEmail(account)) {
            adminEntity = adminDao.getByEmail(account);
        } else {
            adminEntity = adminDao.getByUsername(account);
        }
        System.out.println(adminEntity);
    }

    @Test
    public void getByLoginId() throws Exception {
        Long id = 2L;
        AdminEntity adminEntity = adminDao.getByLoginId(id);
        System.out.println(adminEntity);
    }
}
