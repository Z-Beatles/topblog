<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<header class="main-header">
    <!-- 网站 Logo -->
    <a href="${ctx}/admin" class="logo">
        <span class="logo-mini"><b>T</b>OP</span>
        <span class="logo-lg"><b>Topblog</b><small> Admin</small></span>
    </a>
    <nav class="navbar navbar-static-top">
        <!-- 侧边栏切换按钮 -->
        <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
	        <span class="sr-only">切换侧边栏</span>
        </a>
        <!-- 顶部自定义菜单 -->
        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
                <c:choose>
                    <c:when test="${ch.isUserLogin()}">
                        <!-- 邮件菜单 -->
                        <li class="dropdown messages-menu">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="fa fa-envelope-o"></i><span class="label label-success">1</span>
                            </a>
                            <ul class="dropdown-menu">
                                <li class="header">您有1封新邮件</li>
                                <li>
                                    <ul class="menu">
                                        <li>
                                            <a href="#">
                                                <div class="pull-left"><img src="${ch.userAvatar(req)}" class="img-circle" alt="头像"></div>
                                                <h4>waynechu@waynechu.cn<small><i class="fa fa-clock-o"></i> 5 分</small></h4>
                                                <p>你好，欢迎访问Topblog后台管理系统</p>
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                                <li class="footer"><a href="#">查看所有邮件</a></li>
                            </ul>
                        </li>
                        <!-- 消息菜单 -->
                        <li class="dropdown notifications-menu">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="fa fa-bell-o"></i><span class="label label-warning">3</span>
                            </a>
                            <ul class="dropdown-menu">
                                <li class="header">您有3条未读消息</li>
                                <li>
                                    <ul class="menu">
                                        <li><a href="${ctx}/admin/comment"><i class="fa fa-users text-aqua"></i>收到3条新的留言</a></li>
                                    </ul>
                                </li>
                                <li class="footer"><a href="${ctx}/admin/comment">查看全部</a></li>
                            </ul>
                        </li>
                        <!-- 任务列表 -->
                        <li class="dropdown tasks-menu">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="fa fa-flag-o"></i><span class="label label-danger">2</span>
                            </a>
                            <ul class="dropdown-menu">
                                <li class="header">您有2个新的任务</li>
                                <li>
                                    <ul class="menu">
                                        <li>
                                            <a href="#">
                                                <h3>完成添加文章功能<small class="pull-right">20%</small></h3>
                                                <div class="progress xs">
                                                    <div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                                        <span class="sr-only">20% Complete</span>
                                                    </div>
                                                </div>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#">
                                                <h3>完成文章删除功能<small class="pull-right">40%</small></h3>
                                                <div class="progress xs">
                                                    <div class="progress-bar progress-bar-green" style="width: 40%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                                        <span class="sr-only">40% Complete</span>
                                                    </div>
                                                </div>
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                                <li class="footer">
                                    <a href="#">查看所有任务</a>
                                </li>
                            </ul>
                        </li>
                        <!-- 用户信息 -->
                        <li class="user user-menu">
                            <a href="#" data-toggle="dropdown">
                                <img src="${ch.userAvatar(req)}" class="user-image" alt="头像">
                                <span class="hidden-xs">${ch.userNickname(req)}</span>
                            </a>
                            <ul class="dropdown-menu">
                                <li class="user-header">
                                    <img src="${ch.userAvatar(req)}" class="img-circle" alt="头像">
                                    <p>${ch.userNickname(req)}<small>${ch.userRolename(req)}</small></p>
                                </li>
                                <li class="user-footer">
                                    <div class="pull-left">
                                        <a href="${ctx}/admin/setup/normal" class="btn btn-default btn-flat">设置</a>
                                    </div>
                                    <div class="pull-right">
                                        <a href="${ctx}/logout" class="btn btn-default btn-flat">退出</a>
                                    </div>
                                </li>
                            </ul>
                        </li>
                    </c:when>
                    <c:when test="${!ch.isUserLogin()}">
                        <li>
                            <a href="${ctx}/login">系统错误</a>
                        </li>
                    </c:when>
                </c:choose>
            </ul>
        </div>
    </nav>
</header>