<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <%@ include file="/WEB-INF/views/admin/snippet_header_admin.jsp"%>
</head>
<body class="bdbody skin-purple">
    <div class="wrapper">
        <jsp:include page="/WEB-INF/views/admin/header_admin.jsp"></jsp:include>
        <jsp:include page="/WEB-INF/views/admin/aside_admin.jsp">
            <jsp:param value="article" name="pageCatalog"/>
            <jsp:param value="article_new" name="pageMenu"/>
        </jsp:include>
        <div class="content-wrapper" style="min-height: 1126px;">
            <section class="content-header">
                <h1>
                                                   文章<small>发布</small>
                </h1>
                <ol class="breadcrumb">
                    <li><a href="${ctx}/"><i class="fa fa-dashboard"></i>首页</a></li>
                    <li>文章</li>
                    <li class="active"><a href="${ctx}/article/new">写文章</a></li>
                </ol>
            </section>
            <section class="content">
                <div class="box box-success">
		            <div class="box-body">
		                <h3>文章标题</h3>
		                <input class="form-control input-lg" type="text" placeholder="在此输入文章标题">
			            <div class="form-group">
	                        <h3>文章内容</h3>
	                        <textarea class="form-control" rows="10" placeholder="在此输入文章内容"></textarea>
	                    </div>
	                    <button type="button" class="btn  btn-success btn-lg">发布文章</button>
		            </div>
		            
		        </div>
            </section>
        </div>
        <%@ include file="/WEB-INF/views/admin/footer_admin.jsp"%>
        <div class="control-sidebar-bg" style="position: fixed; height: auto;"></div>
    </div>
</body>
</html>