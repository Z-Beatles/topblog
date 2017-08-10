package cn.waynechu.topblog.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.waynechu.topblog.entity.ArticleEnitiy;

public interface ArticleDao {

    public List<ArticleEnitiy> listArticle(@Param("offset") int offset, @Param("limit") int limit);
    
    public int countArticle();

}
