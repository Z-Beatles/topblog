<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<%@ include file="/WEB-INF/views/admin/common_head_admin.jsp"%>
<link rel="stylesheet" href="${ctx}/static/plugin/editor.md/css/editormd.min.css" />
<link rel="stylesheet" href="${ctx}/static/plugin/bootstrapvalidator-0.5.3/css/bootstrapValidator.min.css">
</head>
<body class="hold-transition skin-purple sidebar-mini">
    <div class="wrapper">
        <jsp:include page="/WEB-INF/views/admin/header_admin.jsp"></jsp:include>
        <jsp:include page="/WEB-INF/views/admin/aside_admin.jsp">
            <jsp:param value="article" name="pageCatalog" />
            <jsp:param value="article_new" name="pageMenu" />
        </jsp:include>
        <div class="content-wrapper" style="min-height: 1126px;">
            <section class="content-header">
                <h1>文章<small>发布</small>
                </h1>
                <ol class="breadcrumb">
                    <li><a href="${ctx}/"><i class="fa fa-dashboard"></i>首页</a></li>
                    <li>文章</li>
                    <li class="active"><a href="${ctx}/article/new">写文章</a></li>
                </ol>
            </section>
            <section class="content">
                <div class="row">
                    <form id="article">
                        <div class="col-md-10">
                            <h3>标题</h3>
                            <input class="form-control input-lg" type="text" id="articleTitle" name="articleTitle"
                                placeholder="在此输入文章标题"><br>
                        </div>
                        <div class="col-md-10">
                            <h3>内容</h3>
                            <div class="editormd" id="editormd">
                                <textarea id="articleContent" name="articleContent" style="display: none;"></textarea>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="box box-primary">
                                <div class="box-header">
                                    <h3 class="box-title">发布</h3>
                                </div>
                                <div class="box-body">
                                    <button id="preview" type="button" class="btn btn-block btn-info">预览</button>
                                    <button id="push" type="button" class="btn btn-block btn-success">发布</button>
                                    <p id="status">状态：未发布</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="box box-success">
                                <div class="box-header">
                                    <h3 class="box-title">分类目录</h3>
                                </div>
                                <div class="box-body">
                                    <div class="alert alert-danger alert-dismissible" id="alert" style="display: none">
                                        <h4><i class="icon fa fa-ban"></i>提示：</h4>请选择文章分类目录！
                                    </div>
                                    <c:forEach items="${ch.getArticleCategory()}" var="category">
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="articleCategoryID" value="${category.category_id}">
                                                <c:out value="${category.category_name}" />
                                            </label>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </section>
        </div>
        <%@ include file="/WEB-INF/views/admin/footer_admin.jsp"%>
        <div class="control-sidebar-bg" style="position: fixed; height: auto;"></div>
    </div>

    <script src="${ctx}/static/plugin/editor.md/editormd.min.js"></script>
    <script src="${ctx}/static/plugin/bootstrapvalidator-0.5.3/js/bootstrapValidator.js"></script>
    <script>
        $(function() {
            var editor = editormd("editormd", {
                path : "/static/plugin/editor.md/lib/",
                placeholder : "提示：点击预览按钮可以预览样式。",
                height : 800,
                watch : false,
                toolbar : true,
                autoHeight : false,
                emoji : true,
                imageUpload : true,
                imageFormats : [ "jpg", "jpeg", "gif", "png", "bmp", "webp" ],
                imageUploadURL : "${ctx}/upload/image"
            });
            function validatorArticle() {
                var v = $("[name=articleCategoryID]:checked").val()
                console.log(v);
                if (v) {
                    return true;
                } else {
                    $("#alert").slideDown();
                    return false;
                }
            }
            $("#push").click(function() {
                if (validatorArticle()) {
                    var url = "/article/new/post";
                    var data = $("#article").serialize();
                    $.post(url, data, function(result) {
                        if (result && result['success']) {
                            var newDate = new Date();
                            newDate.setTime(result['articleTime']);
                            var status = $("<p style='display: none' id='status'></p>").text("更新时间：" + newDate.toLocaleString());
                            $("#status").replaceWith(status);
                            if($("#alert").show()){
                                $("#alert").hide();
                            }
                            $("#status").show(1000);
                        }
                    });
                }
            });
        });
    </script>
</body>
</html>