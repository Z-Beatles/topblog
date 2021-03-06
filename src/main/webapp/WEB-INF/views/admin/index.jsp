<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <%@ include file="/WEB-INF/views/admin/common_head.jsp"%>
</head>
<body class="hold-transition skin-purple sidebar-mini">
    <div class="wrapper">
        <jsp:include page="/WEB-INF/views/admin/header.jsp"></jsp:include>
        <jsp:include page="/WEB-INF/views/admin/aside.jsp">
            <jsp:param value="topblog" name="pageCatalog"/>
            <jsp:param value="topblog" name="pageMenu"/>
        </jsp:include>
        <div class="content-wrapper" style="min-height: 1126px;">
            <section class="content">
                <h1 style="text-align: center; padding: 300px 0;">欢迎进入 Topblog 管理后台</h1>
            </section>
        </div>
        <%@ include file="/WEB-INF/views/admin/footer.jsp"%>
        <div class="control-sidebar-bg" style="position: fixed; height: auto;"></div>
    </div>
</body>
</html>