package cn.waynechu.topblog.shiro.factory;

import java.util.LinkedHashMap;

public class FilterChainDefinitionMapBuilder {

    public LinkedHashMap<String, String> buildFilterChainDefinitionMap() {

        // 如果规则较多，可以存入数据库并从数据库中获取
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        map.put("/static/**", "anon");
        map.put("/logout", "logout");
        map.put("/admin/logout", "logout");
        map.put("/admin/login", "anon");

        // 过滤admin路径下所有请求
        // map.put("/admin/**", "roles[admin]");//设置角色
        map.put("/admin/**", "perms[admin]");// 设置权限
        return map;
    }
}
