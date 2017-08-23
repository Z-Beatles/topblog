# [Topblog](http://topblog.top)

## 数据库选择

### 本地数据库

**注意：远程数据库名为topblog**

如果是想使用自己的本地数据库，将 resource 目录下的 sql 文件导入 MySQL 中。
并修改 jdbc.properties 文件:
 
```shell
driverClass=com.mysql.jdbc.Driver
jdbcUrl=jdbc:mysql://localhost:3306/topblog?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC
user=root
password=yourpassword
```

### 远程数据库（默认）

**注意：远程数据库名为topblog353，因为服务器上有一个topblog数据库了，加个353区分开来**

可用帐号如下，请自行选择：
user=xiacunhai  password=rh353hylcg
user=yuanxiaosong  password=rh353hylcg
user=waynechu  password=rh353hylcg

```shell
driverClass=com.mysql.jdbc.Driver
jdbcUrl=jdbc:mysql://139.59.228.231:3306/topblog353?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC
user=waynechu
password=rh353hylcg
```
