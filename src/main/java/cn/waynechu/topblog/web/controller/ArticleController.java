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
import cn.waynechu.topblog.service.ArticleService;
import cn.waynechu.topblog.util.ObjectUtil;
import cn.waynechu.topblog.util.WebUtil;

@Controller
@RequestMapping(value="/article")
public class ArticleController extends BaseController{
    
    @Autowired
    private ArticleService articleService;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "admin/article/list";
    }
    
    @RequestMapping(value = "/list/table.json", method = RequestMethod.POST)
    @ResponseBody
    public String listArticle(DataTableParam tableParam) {
        return "{\"success\":true,\"data\":[{\"category_name\":\"JavaSE\",\"category_id\":1,\"category_count\":2},{\"category_name\":\"JavaEE\",\"category_id\":2,\"category_count\":0},{\"category_name\":\"SQL\",\"category_id\":3,\"category_count\":0},{\"category_name\":\"Web\",\"category_id\":4,\"category_count\":0},{\"category_name\":\"计算机网络\",\"category_id\":5,\"category_count\":0},{\"category_name\":\"Git\",\"category_id\":6,\"category_count\":0},{\"category_name\":\"C\",\"category_id\":7,\"category_count\":0},{\"category_name\":\"计算机组成原理\",\"category_id\":8,\"category_count\":0},{\"category_name\":\"算法设计与分析\",\"category_id\":9,\"category_count\":1},{\"category_name\":\"操作系统\",\"category_id\":10,\"category_count\":0}],\"draw\":1,\"recordsTotal\":11,\"recordsFiltered\":11}";
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
        List<?> data = ObjectUtil.toDataList(result.target(), "category_id", "category_name","category_count");
        return WebUtil.dataTable(data, tableParam.getDraw(), result.attr("totalRecords"),
                result.attr("totalDisplayRecords"));
    }
    
    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public String tags() {
        return "admin/article/tags";
    }
    
}
