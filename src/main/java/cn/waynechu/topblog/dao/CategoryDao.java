package cn.waynechu.topblog.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao {

    List<Map<String, String>> listCategory(@Param("offset") int offset, @Param("limit") int limit);

    int countCategory();

    List<Map<String, String>> getCategory();

    int saveCategory(@Param("categoryName") String categoryName);

    void deleteCategory(@Param("categoryId") String categoryId);

}
