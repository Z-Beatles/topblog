package cn.waynechu.topblog.dao;

import java.util.List;

/**
 * Created by xiach on 2017/9/26.
 */
public interface BaseDao<T>{

    /**
     * 基础的增删改查
     */
    Integer insert(T t);

    Integer delete(T t);

    Integer update(T t);

    List<T> select(T t);

    Integer count(T t);
}
