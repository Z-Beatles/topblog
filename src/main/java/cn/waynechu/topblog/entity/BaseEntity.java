package cn.waynechu.topblog.entity;

import lombok.Data;

import java.io.Serializable;
/**
 * 页面分页
 * Created by xiach on 2017/9/26.
 */
@Data
public class BaseEntity implements Serializable {
    private Integer pageIndex;//当前页起始条
    private Integer pageSize;//页面大小

}
