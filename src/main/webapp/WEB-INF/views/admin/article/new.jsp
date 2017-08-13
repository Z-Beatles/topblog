<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <%@ include file="/WEB-INF/views/admin/common_head_admin.jsp"%>
    <link rel="stylesheet" href="${ctx}/static/plugin/editor.md-1.5.0/css/editormd.min.css" />
</head>
<body class="hold-transition skin-purple sidebar-mini">
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
                
                <div class="row">
	                <div class="col-md-10">
		                <h3>标题</h3>
			                <input class="form-control input-lg" type="text" placeholder="在此输入文章标题"><br>
		                </div>
                    <div class="col-md-10">
                       <h3>内容</h3>
                       <div id="editormd">
                           <textarea style="display:none;"></textarea>
                           <textarea class="editormd-html-textarea" name="editorhtml" id="editorhtml"></textarea>
                       </div>
                    </div>
	                <div class="col-md-2">
                        <div class="box box-primary">
					        <div class="box-header"><h3 class="box-title">发布</h3></div>
					        <div class="box-body">
					            <button type="button" class="btn btn-block btn-info">预览</button>
					            <button type="button" class="btn btn-block btn-success">发布</button>
					        </div>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="box box-success">
                            <div class="box-header">
                                <h3 class="box-title">形式</h3>
                            </div>
                            <div class="box-body">
                                <div class="form-group">
					                <div class="radio">
					                    <label><input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked="checked">
					                                                            标准
					                    </label>
					                </div>
					                <div class="radio">
					                    <label><input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
					                                                            日志
					                    </label>
					                </div>
					            </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
        <%@ include file="/WEB-INF/views/admin/footer_admin.jsp"%>
        <div class="control-sidebar-bg" style="position: fixed; height: auto;"></div>
    </div>
    
    <script type="text/javascript" src="${ctx}/static/plugin/editor.md-1.5.0/editormd.min.js"></script>
	<script type="text/javascript">
	    $(function() {
	        var editor = editormd("editormd", {
	        	path : "${ctx}/static/plugin/editor.md-1.5.0/lib/",
	        	placeholder : "提示：点击预览按钮可以预览样式。",
	            height  : 800,
	            watch : false,
	            toolbar : true,
	            autoHeight : false,
	            emoji : true,
	            imageUpload    : true,
                imageFormats   : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
                imageUploadURL : "${ctx}/upload/image",
                saveHTMLToTextarea : true
	        });
	    });
</script>
</body>
</html>