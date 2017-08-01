<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<title>用户主页面</title>
</head>
<body>
	<h2>用户主页面</h2>

	<shiro:user>欢迎您的登录~~<br>
		<br>
		<p>${ch.isAdminLogin()}</p>

		<a href="/logout">退出</a>
	</shiro:user>



	<shiro:guest>
		<p>欢迎游客访问~</p>
		<a href="/login">点击登录</a>
	</shiro:guest>

	<shiro:hasRole name="user">
		<br>
		<br>
		<a href="/user">访问用户界面</a>
	</shiro:hasRole>

	<shiro:hasRole name="admin">
		<br>
		<br>
		<a href="/admin">访问后台管理界面</a>
	</shiro:hasRole>

</body>
</html>