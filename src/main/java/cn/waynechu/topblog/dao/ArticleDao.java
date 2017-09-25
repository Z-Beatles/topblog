package cn.waynechu.topblog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import cn.waynechu.topblog.entity.ArticleEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleDao {

    List<ArticleEntity> listArticle(@Param("offset") int offset, @Param("limit") int limit);

    int countArticle();

    int saveArticle(@Param("articleEnitiy") ArticleEntity articleEnitiy);

    void deleteArticle(@Param("articleId") String articleId);

    ArticleEntity getArticleByTitle(String articleTitle);

    ArticleEntity getArticleById(Long articleId);

    int updateArticle(@Param("articleEnitiy")ArticleEntity articleEnitiy);
}
