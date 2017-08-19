package cn.waynechu.topblog.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.waynechu.topblog.Result;
import cn.waynechu.topblog.dao.ArticleDao;
import cn.waynechu.topblog.dao.CategoryDao;
import cn.waynechu.topblog.dto.DataTableParam;
import cn.waynechu.topblog.entity.ArticleEntity;

@Service
public class ArticleService {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ArticleDao articleDao;

    public Result<List<Map<String, String>>> listCategory(DataTableParam tableParam) {
        List<Map<String, String>> list = categoryDao.listCategory(tableParam.getStart(), tableParam.getLength());
        int count = categoryDao.countCategory();
        Result<List<Map<String, String>>> result = Result.target(list);
        result.attr("totalRecords", count);
        result.attr("totalDisplayRecords", count);
        return result;
    }

    public Result<List<ArticleEntity>> listArticle(DataTableParam tableParam) {
        List<ArticleEntity> list = articleDao.listArticle(tableParam.getStart(), tableParam.getLength());
        int count = articleDao.countArticle();
        Result<List<ArticleEntity>> result = Result.target(list);
        result.attr("totalRecords", count);
        result.attr("totalDisplayRecords", count);
        return result;
    }

    public Date saveArticle(ArticleEntity articleEnitiy) {
        Date articleTime = new Date();
        articleEnitiy.setArticleTime(articleTime);
        boolean flag = articleDao.saveArticle(articleEnitiy);
        if(flag) {
            return articleTime;
        }
        return null;
    }
}
