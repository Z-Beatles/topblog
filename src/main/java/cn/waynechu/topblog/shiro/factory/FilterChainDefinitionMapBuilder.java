package cn.waynechu.topblog.shiro.factory;

import java.util.LinkedHashMap;

public class FilterChainDefinitionMapBuilder {

    public LinkedHashMap<String, String> buildFilterChainDefinitionMap() {

        // 如果规则较多，可以存入数据库并从数据库中获取
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        map.put("/static/**", "anon");
        map.put("/logout", "logout");
        map.put("/login", "anon");
        map.put("/**", "perms[admin]");// 设置权限
        return map;
    }
}
