<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <%@ include file="/WEB-INF/views/admin/snippet_header_admin.jsp"%>
</head>
<body class="skin-purple sidebar-mini">
    <div class="wrapper">
        <jsp:include page="/WEB-INF/views/admin/header_admin.jsp"></jsp:include>
        <jsp:include page="/WEB-INF/views/admin/aside_admin.jsp">
            <jsp:param value="tools" name="pageCatalog"/>
            <jsp:param value="tools_export" name="pageMenu"/>
        </jsp:include>
        <div class="content-wrapper" style="min-height: 1126px;">
            <section class="content">
                <h1 style="text-align: center; padding: 300px 0;">数据导出界面</h1>
            </section>
        </div>
        <%@ include file="/WEB-INF/views/admin/footer_admin.jsp"%>
        <div class="control-sidebar-bg" style="position: fixed; height: auto;"></div>
    </div>
</body>
</html>