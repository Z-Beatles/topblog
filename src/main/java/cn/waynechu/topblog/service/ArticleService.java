package cn.waynechu.topblog.service;

import cn.waynechu.topblog.AppException;
import cn.waynechu.topblog.Result;
import cn.waynechu.topblog.dao.ArticleDao;
import cn.waynechu.topblog.dao.CategoryDao;
import cn.waynechu.topblog.entity.ArticleEntity;
import cn.waynechu.topblog.model.DataTableParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
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

    public List<Map<String, String>> getArticleCategory() {
        return categoryDao.getCategory();
    }

    public Result<List<ArticleEntity>> listArticle(DataTableParam tableParam) {
        List<ArticleEntity> list = articleDao.listArticle(tableParam.getStart(), tableParam.getLength());
        int count = articleDao.countArticle();
        Result<List<ArticleEntity>> result = Result.target(list);
        result.attr("totalRecords", count);
        result.attr("totalDisplayRecords", count);
        return result;
    }

    @Transactional
    public Date saveArticle(ArticleEntity articleEnitiy) {
        Date articleTime = new Date();
        articleEnitiy.setArticleTime(articleTime);
        ArticleEntity oldArticle = articleDao.getArticleByTitle(articleEnitiy.getArticleTitle());
        if (oldArticle == null) {
            if (articleDao.saveArticle(articleEnitiy) == 0) {
                throw new AppException(2, "添加文章失败");
            }
        } else {
            articleEnitiy.setArticleId(oldArticle.getArticleId());
            int flag = articleDao.updateArticle(articleEnitiy);
            if (flag == 0) {
                throw new AppException(2, "更新文章失败");
            }
        }
        return articleTime;
    }

    @Transactional
    public void deleteArticle(String articleId) {
        articleDao.deleteArticle(articleId);
    }

    @Transactional
    public boolean saveCategory(String categoryName) {
        int flag = categoryDao.saveCategory(categoryName);
        if (flag == 0) {
            throw new AppException(2, "添加目录失败");
        }
        return true;
    }

    public ArticleEntity getArticleById(Long articleId) {
        return articleDao.getArticleById(articleId);
    }

    @Transactional
    public void deleteCategory(String categoryId) {
        categoryDao.deleteCategory(categoryId);
    }
}
