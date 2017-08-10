package cn.waynechu.topblog.entity;

import java.util.Date;

public class ArticleEnitiy {
    /** 文章ID **/
    private long articleId;
    /** 文章标题 **/
    private String articleTitle;
    /** 文章作者 **/
    private String articleAuthor;
    /** 分类目录 **/
    private String articleCategory;
    /** 文章标签 **/
    private String articleTag;
    /** 发布时间 **/
    private Date articleTime;
    /** 文章内容 **/
    private String articleContent;

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor;
    }

    public String getArticleCategory() {
        return articleCategory;
    }

    public void setArticleCategory(String articleCategory) {
        this.articleCategory = articleCategory;
    }

    public String getArticleTag() {
        return articleTag;
    }

    public void setArticleTag(String articleTag) {
        this.articleTag = articleTag;
    }

    public Date getArticleTime() {
        return articleTime;
    }

    public void setArticleTime(Date articleTime) {
        this.articleTime = articleTime;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    @Override
    public String toString() {
        return "ArticleEnitiy [articleId=" + articleId + ", articleTitle=" + articleTitle + ", articleAuthor="
                + articleAuthor + ", articleCategory=" + articleCategory + ", articleTag=" + articleTag
                + ", articleTime=" + articleTime + ", articleContent=" + articleContent + "]";
    }

}
