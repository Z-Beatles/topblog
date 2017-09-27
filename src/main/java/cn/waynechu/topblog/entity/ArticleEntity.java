package cn.waynechu.topblog.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper=false)
@Data
public class ArticleEntity implements Serializable{
    /** 文章ID **/
    private long articleId;
    /** 文章标题 **/
    private String articleTitle;
    /** 文章作者 **/
    private long articleAuthorID;
    private String articleAuthor;
    /** 分类目录 **/
    private long articleCategoryID;
    private String articleCategory;
    /** 文章标签 **/
    private String articleTag;
    /** 发布时间 **/
    private Date articleTime;
    /** 文章内容 **/
    private String articleContent;

}
