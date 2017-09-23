package cn.waynechu.topblog.web.controller.admin;

import cn.waynechu.topblog.Constaint;
import cn.waynechu.topblog.Result;
import cn.waynechu.topblog.base.BaseController;
import cn.waynechu.topblog.entity.ArticleEntity;
import cn.waynechu.topblog.model.DataTableParam;
import cn.waynechu.topblog.service.ArticleService;
import cn.waynechu.topblog.util.ObjectUtil;
import cn.waynechu.topblog.util.WebUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin/article")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "admin/article/list";
    }

    @RequestMapping(value = "/list.json", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, ?> listArticle(DataTableParam tableParam) {
        Result<List<ArticleEntity>> result = articleService.listArticle(tableParam);
        List<?> data = ObjectUtil.toDataList(result.target(), "articleId", "articleTitle", "articleAuthor",
                "articleCategory", "articleTag", "articleTime", "articleContent");
        return WebUtil.dataTable(data, tableParam.getDraw(), result.attr("totalRecords"), result.attr("totalDisplayRecords"));
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String add(Model model) {
        List<Map<String, String>> articleCategory = articleService.getArticleCategory();
        model.addAttribute("articleCategory", articleCategory);
        return "admin/article/new";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody

    public Map<String, Object> newArticle(ArticleEntity articleEntity) {
        Map<String, Object> principal = (Map<String, Object>) SecurityUtils.getSubject().getPrincipal();
        Long id = (Long) principal.get("loginId");
        articleEntity.setArticleAuthorID(id);
        Date articleTime = articleService.saveArticle(articleEntity);
        return WebUtil.success("添加成功", String.valueOf(articleTime.getTime()));
    }

    @RequestMapping(value = "/delete/{articleId}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> deleteArticle(@PathVariable String articleId) {
        articleService.deleteArticle(articleId);
        return WebUtil.success(Constaint.DELETE_SUCCESS);
    }

    @RequestMapping(value = "/deleteSelect", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> deleteArticles(String ids) {
        String[] item = ids.split(",");
        for (int i = 0; i < item.length; i++) {
            articleService.deleteArticle(item[i]);
        }
        return WebUtil.success(Constaint.DELETE_SUCCESS);
    }

    @RequestMapping(value = "/new/preview/{articleTitle}", method = RequestMethod.GET)
    public void preview(@PathVariable("articleTitle") String articleTitle) {
        // TODO
    }

    @RequestMapping(value = "/edit/{articleId}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("articleId") Long articleId) {
        ArticleEntity articleEntity = articleService.getArticleById(articleId);
        model.addAttribute("articleTitle",articleEntity.getArticleTitle());
        model.addAttribute("articleCategoryID",String.valueOf(articleEntity.getArticleCategoryID()));
        model.addAttribute("articleContent",articleEntity.getArticleContent());
        model.addAttribute("articleTime",articleEntity.getArticleTime());

        List<Map<String, String>> articleCategory = articleService.getArticleCategory();
        model.addAttribute("articleCategory", articleCategory);
        return "admin/article/new";
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public String category() {
        return "admin/article/category";
    }

    @RequestMapping(value = "/category/new", method = RequestMethod.POST)
    @ResponseBody
    public String newCategory(String categoryName) {
        if (articleService.addCategory(categoryName)) {
            return "{\"success\":1}";
        }
        return "";
    }

    @RequestMapping(value = "/category/table.json", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, ?> listCategory(DataTableParam tableParam) {
        Result<List<Map<String, String>>> result = articleService.listCategory(tableParam);
        List<?> data = ObjectUtil.toDataList(result.target(), "category_id", "category_name", "category_count");
        return WebUtil.dataTable(data, tableParam.getDraw(), result.attr("totalRecords"),
                result.attr("totalDisplayRecords"));
    }

    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public String tags() {
        return "admin/article/tags";
    }

}
