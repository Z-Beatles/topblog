package cn.waynechu.topblog.service;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import cn.waynechu.topblog.Result;
import cn.waynechu.topblog.dao.ArticleDao;
import cn.waynechu.topblog.dao.CategoryDao;
import cn.waynechu.topblog.entity.ArticleEnitiy;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class ArticleServiceTest {

    @Resource
    private CategoryDao categoryDao;
    
    @Resource
    private ArticleDao articleDao;
    
    @Test
    public void listCategory() throws Exception {
        List<Map<String, String>> list = categoryDao.listCategory(0,10);
        int count = categoryDao.countCategory();
        Result<List<Map<String, String>>> result = Result.target(list);
        result.attr("totalRecords", count);
        result.attr("totalDisplayRecords", list.size());
        System.out.println(result);
    }
    
    @Test
    public void listArticle()throws Exception {
        List<ArticleEnitiy> list = articleDao.listArticle(0,10);
        int count = articleDao.countArticle();
        Result<List<ArticleEnitiy>> result = Result.target(list);
        result.attr("totalRecords", count);
        result.attr("totalDisplayRecords", count);
        System.out.println(result);
    }

}
