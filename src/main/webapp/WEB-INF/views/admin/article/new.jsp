<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <%@ include file="/WEB-INF/views/admin/common_head.jsp" %>
</head>
<body class="hold-transition skin-purple sidebar-mini">
<div class="wrapper">
    <jsp:include page="/WEB-INF/views/admin/header.jsp"/>
    <jsp:include page="/WEB-INF/views/admin/aside.jsp">
        <jsp:param value="article" name="pageCatalog"/>
        <jsp:param value="article_new" name="pageMenu"/>
    </jsp:include>
    <div class="content-wrapper" style="min-height: 1126px;">
        <section class="content-header">
            <h1>文章
                <small>发布</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="${ctx}/"><i class="fa fa-dashboard"></i>首页</a></li>
                <li>文章</li>
                <li class="active"><a href="${ctx}/admin/article/new">写文章</a></li>
            </ol>
        </section>
        <section class="content">
            <div class="box box-primary">
                <div class="box-header with-border">
                    <button class="btn btn-primary" onclick="location='${ctx}/admin/article/list'">返回</button>
                </div>
                <div class="box-body">
                    <form id="article">
                        <div class="col-md-10 form-group">
                            <h3><label class="control-label" for="articleTitle">标题</label></h3>
                            <input class="form-control input-lg" type="text" id="articleTitle" name="articleTitle" value="${articleTitle}"
                                   placeholder="在此输入文章标题">
                        </div>
                        <div class="col-md-10 form-group">
                            <h3><label class="control-label" for="articleContent">内容</label></h3>
                            <div class="editormd" id="editormd">
                                <textarea class="form-control" id="articleContent" name="articleContent"
                                          style="display: none;">${articleContent}</textarea>
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
                                </div>
                                <div class="box-footer">
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
                                    <div class="alert alert-danger alert-" id="alert" style="display: none">
                                        <h4><i class="icon fa fa-ban"></i>提示：</h4>请选择文章分类目录！
                                    </div>
                                    <c:forEach items="${articleCategory}" var="category">
                                        <div class="radio">
                                            <label>
                                                <c:choose>
                                                    <c:when test="${category.category_id==articleCategoryID}">
                                                        <input type="radio" name="articleCategoryID" value="${category.category_id}" checked="checked"/>
                                                        <c:out value="${category.category_name}"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" name="articleCategoryID" value="${category.category_id}"/>
                                                        <c:out value="${category.category_name}"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </label>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </section>
    </div>
    <%@ include file="/WEB-INF/views/admin/footer.jsp" %>
</div>

<script>
    $(function () {
        var editor = editormd("editormd", {
            path: "/static/plugin/editor.md/lib/",
            placeholder: "提示：点击预览按钮可以预览样式。",
            height: 800,
            watch: false,
            toolbar: true,
            autoHeight: false,
            emoji: true,
            imageUpload: true,
            imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
            imageUploadURL: "${ctx}/upload/image"
        });

        function validatorArticle() {
            if ($("[name=articleCategoryID]:checked").val()) {
                return true;
            } else {
                $("#alert").slideDown();
                return false;
            }
        }

        $("#article").bootstrapValidator({
            fields: {
                articleTitle: {
                    validators: {
                        notEmpty: {message: '文章标题不能为空!'}
                    }
                }
            }
        });

        (function changeDate() {
            var status = $("<p id='status'></p>").text("状态：未发布");
            if (${not empty articleTime}) {
                var newDate = new Date("${articleTime}");
                status = $("<p id='status'></p>").text("更新时间：" + newDate.toLocaleString());
                $("#status").replaceWith(status);
            } else {
                $("#status").replaceWith(status);
            }
        })();

        $("#push").click(function () {
            $("#article").data('bootstrapValidator').validate();
            if (validatorArticle()) {
                var url = "/admin/article/new?debug";
                var data = $("#article").serialize();
                $.post(url, data, function (result) {
                    if (result && result['success']) {
                        var newDate = new Date();
                        newDate.setTime(result['data']);
                        var status = $("<p style='display: none' id='status'></p>").text("更新时间：" + newDate.toLocaleString());
                        $("#status").replaceWith(status);
                        if ($("#alert").show()) {
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