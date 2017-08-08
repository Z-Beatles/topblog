<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<header class="main-header">
    <nav class="navbar navbar-static-top" style="margin-left:0;">
        <div class="navbar-header">
            <a href="${ctx}/admin" class="logo">
			<span class="logo-lg"><b>Topblog<small> Admin</small></b></span></a>
        </div>
        <div class="collapse navbar-collapse pull-left">
            <ul class="nav navbar-nav">
            </ul>
        </div>
        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
                <c:choose>
                    <c:when test="${ch.isAdminLogin()}">
                        <li class="user user-menu">
                            <a href="#" data-toggle="dropdown"><img src="${ch.adminAvatar(req)}" class="user-image"><span>${ch.adminNickname(req)}</span></a>
                            <ul class="dropdown-menu">
                                <li class="user-header">
                                    <img src="${ch.adminAvatar(req)}" class="img-circle">
                                    <p>${ch.adminNickname(req)}<small>${ch.adminRolename(req)}</small></p>
                                </li>
                                <li class="user-footer">
                                    <div class="pull-left">
                                        <a href="${ctx}/setup/normal" class="btn btn-default btn-flat">设置</a>
                                    </div>
                                    <div class="pull-right">
                                        <a href="${ctx}/logout" class="btn btn-default btn-flat">退出</a>
                                    </div>
                                </li>
                            </ul>
                        </li>
                    </c:when>
                    <c:when test="${!ch.isAdminLogin()}">
                        <li>
                            <a href="${ctx}/login">错误</a>
                        </li>
                    </c:when>
                </c:choose>
            </ul>
        </div>
    </nav>
</header>