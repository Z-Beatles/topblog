package cn.waynechu.topblog.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.waynechu.topblog.entity.ArticleEntity;

public interface ArticleDao {

    public List<ArticleEntity> listArticle(@Param("offset") int offset, @Param("limit") int limit);
    
    public int countArticle();

    public boolean saveArticle(@Param("articleEnitiy")ArticleEntity articleEnitiy);

}
