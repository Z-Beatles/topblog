package cn.waynechu.topblog.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.waynechu.topblog.Result;
import cn.waynechu.topblog.dao.ArticleDao;
import cn.waynechu.topblog.dao.CategoryDao;
import cn.waynechu.topblog.dto.DataTableParam;
import cn.waynechu.topblog.entity.ArticleEnitiy;

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

    public Result<List<ArticleEnitiy>> listArticle(DataTableParam tableParam) {
        List<ArticleEnitiy> list = articleDao.listArticle(tableParam.getStart(), tableParam.getLength());
        int count = articleDao.countArticle();
        Result<List<ArticleEnitiy>> result = Result.target(list);
        result.attr("totalRecords", count);
        result.attr("totalDisplayRecords", count);
        return result;
    }
}
