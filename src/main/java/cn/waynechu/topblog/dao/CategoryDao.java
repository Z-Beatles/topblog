package cn.waynechu.topblog.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface CategoryDao {

    public List<Map<String, String>> listCategory(@Param("offset") int offset, @Param("limit") int limit);

    public int countCategory();
    
    public List<Map<Integer, String>> getCategory();
    
}
