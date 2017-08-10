package cn.waynechu.topblog.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.waynechu.topblog.Result;
import cn.waynechu.topblog.base.BaseController;
import cn.waynechu.topblog.dto.DataTableParam;
import cn.waynechu.topblog.entity.ArticleEnitiy;
import cn.waynechu.topblog.service.ArticleService;
import cn.waynechu.topblog.util.ObjectUtil;
import cn.waynechu.topblog.util.WebUtil;

@Controller
@RequestMapping(value = "/article")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "admin/article/list";
    }

    @RequestMapping(value = "/list/table.json", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, ?> listArticle(DataTableParam tableParam) {
        Result<List<ArticleEnitiy>> result = articleService.listArticle(tableParam);
        List<?> data = ObjectUtil.toDataList(result.target(), "articleId", "articleTitle", "articleAuthor",
                "articleCategory", "articleTag", "articleTime", "articleContent");
        return WebUtil.dataTable(data, tableParam.getDraw(), result.attr("totalRecords"),
                result.attr("totalDisplayRecords"));
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newArticle() {
        return "admin/article/new";
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public String category() {
        return "admin/article/category";
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
