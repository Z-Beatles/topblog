<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <%@ include file="/WEB-INF/views/admin/common_head.jsp" %>
</head>
<body class="hold-transition skin-purple sidebar-mini">
<div class="wrapper">
    <jsp:include page="/WEB-INF/views/admin/header.jsp"/>
    <jsp:include page="/WEB-INF/views/admin/aside.jsp">
        <jsp:param value="user" name="pageCatalog"/>
        <jsp:param value="user_add" name="pageMenu"/>
    </jsp:include>
    <div class="content-wrapper" style="min-height: 1126px;">
        <h1 style="text-align: center;">用户信息</h1>

        <section class="content">
            <form style="text-align: center;">
                用户名：<input name="username_txt" id="username"></br></br>
                密码：<input name="username_txt" id="nickname"></br></br>
                电话：<input name="username_txt" id="mobile"></br></br>
                邮件：<input name="username_txt" id="email"></br></br>
                头像：<input name="username_txt" id="avatar"></br></br>
                角色：<input name="username_txt" id="role"></br></br>
            </form>
        </section>

    </div>
    <%@ include file="/WEB-INF/views/admin/footer.jsp" %>
</div>
</body>
</html>