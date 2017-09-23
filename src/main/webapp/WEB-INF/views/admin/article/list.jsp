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
        <jsp:param value="article" name="pageCatalog"/>
        <jsp:param value="article_list" name="pageMenu"/>
    </jsp:include>
    <div class="content-wrapper" style="min-height: 1126px;">
        <section class="content-header">
            <h1>文章
                <small>列表</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="${ctx}/admin"><i class="fa fa-dashboard"></i>首页</a></li>
                <li>文章</li>
                <li class="active"><a href="${ctx}/admin/article/list">所有文章</a></li>
            </ol>
        </section>
        <section class="content">
            <div class="box box-primary">
                <div class="box-header with-border">
                    <button type="button" class="btn btn-primary" onclick="location='${ctx}/admin/article/new'">写文章</button>
                    <button type="button" class="btn btn-primary" id="deleteSelect">批量删除</button>
                </div>
                <div class="box-body">
                    <table id="table" class="table table-bordered table-hover table-striped" style="width: 100%">
                        <thead>
                        <tr>
                            <th><label for="checkAll"></label><input type="checkbox" id="checkAll"/></th>
                            <th>序号</th>
                            <th>标题</th>
                            <th>作者</th>
                            <th>分类目录</th>
                            <th>发布日期</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </section>
    </div>
    <%@ include file="/WEB-INF/views/admin/footer.jsp" %>
</div>

<script>
    $(document).ready(function () {
        var dt = $('#table').DataTable({
            serverSide: true,
            processing: true,
            ajax: {url: '${ctx}/admin/article/list.json', type: 'GET'},
            dom: "<'row'<'col-sm-6'l><'col-sm-6'f>><'row'<'col-sm-12'tr>><'row'<'col-sm-6'i><'col-sm-6'p>>",
            pagingType: 'full_numbers',
            ordering: false,
            language: {
                "search": "搜索:",
                "lengthMenu": "每页显示 _MENU_ 条记录",
                "zeroRecords": "没有检索到数据",
                "info": "显示 _START_ 至 _END_ 条 &nbsp;&nbsp;共 _TOTAL_ 条",
                "infoFiltered": "(筛选自 _MAX_ 条数据)",
                "infoEmpty": "没有数据",
                "processing": "正在加载数据...",
                "paginate": {
                    "first": "首页",
                    "previous": "上一页",
                    "next": "下一页",
                    "last": "末页"
                }
            },
            columns: [
                {
                    data: "articleId",
                    render: function (data) {
                        return '<input type="checkbox" value="' + data + '"/>';
                    }
                },
                {
                    data: "articleId"
                },
                {
                    data: "articleTitle"
                },
                {
                    data: "articleAuthor"
                },
                {
                    data: "articleCategory"
                },
                {
                    data: "articleTime",
                    render: function (data) {
                        var newDate = new Date(data);
                        return newDate.toLocaleString();
                    }
                },
                {
                    data: "articleId",
                    render: function (data) {
                        var content = '<div class="btn-group">';
                        content += '    <button type="button" class="btn btn-primary" onclick="location=\'${ctx}/admin/article/edit/' + data + '\'">编辑</button>';
                        content += '    <button type="button" class="btn btn-primary" value="' + data + '" onclick="deleteAction(this)" >删除</button>';
                        content += '</div>';
                        return content;
                    }
                }
            ]
        });

        $("#checkAll").click(function () {
            if (this.checked) {
                $('table tr td input[type=checkbox]').prop('checked', true);
            } else {
                $('table tr td input[type=checkbox]').prop('checked', false);
            }
        });

        $("#deleteSelect").click(function () {
            var checks = $("table tr td input[type=checkbox]:checked");
            if (checks.length === 0) {
                alert('请至少选择一个删除项！');
                return false;
            }
            if (confirm('警告：确定要删除这些选项吗?')) {
                var checkData = [];
                checks.each(function () {
                    checkData.push($(this).val());
                });
                var url = "/admin/article/deleteSelect";
                $.get(url, {ids: checkData.toString()}, function (result) {
                    if (result["success"] === false) {
                        alert("批量删除失败");
                    } else {
                        alert("批量删除成功");
                        location.reload();
                    }
                });
            }
        });
    });

    function deleteAction(obj) {
        var url = "${ctx}/admin/article/delete/" + obj.value;
        if (confirm("警告：确定删除该文章么？")) {
            $.get(url, function (result) {
                if (result["success"] === false) {
                    alert(result);
                } else {
                    alert("删除成功！");
                    location.reload();
                }
            });
        }
    }

</script>
</body>
</html>