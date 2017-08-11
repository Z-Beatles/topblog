<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <%@ include file="/WEB-INF/views/admin/common_head_admin.jsp"%>
</head>
<body class="hold-transition skin-purple sidebar-mini">
    <div class="wrapper">
        <jsp:include page="/WEB-INF/views/admin/header_admin.jsp"></jsp:include>
        <jsp:include page="/WEB-INF/views/admin/aside_admin.jsp">
            <jsp:param value="user" name="pageCatalog"/>
            <jsp:param value="user_list" name="pageMenu"/>
        </jsp:include>
        <div class="content-wrapper" style="min-height: 1126px;">
            <section class="content-header">
                <h1>
                                                   用户<small>列表</small>
                </h1>
                <ol class="breadcrumb">
                    <li><a href="${ctx}/"><i class="fa fa-dashboard"></i>首页</a></li>
                    <li>用户</li>
                    <li class="active"><a href="${ctx}/user/list">所有用户</a></li>
                </ol>
            </section>
            <section class="content">
                <div class="box">
                    <div class="box-header with-border">
                        <a class="btn btn-primary" href="${ctx}/user/add">添加用户</a>
                    </div>
                    <div class="box-body">
                        <table id="table" class="table table-bordered table-hover table-striped">
                            <thead>
                                <tr>
                                    <th>用户名</th>
                                    <th>昵称</th>
                                    <th>邮箱</th>
                                    <th>电话</th>
                                    <th>用户类型</th>
                                    <th>文章</th>
                                </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </section>
        </div>
        <%@ include file="/WEB-INF/views/admin/footer_admin.jsp"%>
        <div class="control-sidebar-bg" style="position: fixed; height: auto;"></div>
    </div>
</body>
</html>