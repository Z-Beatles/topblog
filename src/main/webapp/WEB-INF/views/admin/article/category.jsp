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
        <jsp:param value="article_category" name="pageMenu"/>
    </jsp:include>
    <div class="content-wrapper" style="min-height: 1126px;">
        <section class="content-header">
            <h1>目录
                <small>列表</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="${ctx}/"><i class="fa fa-dashboard"></i>首页</a></li>
                <li>文章</li>
                <li class="active"><a href="${ctx}/admin/article/category">分类目录</a></li>
            </ol>
        </section>
        <section class="content">
            <div class="box box-primary">
                <div class="box-header with-border">
                    <button type="button" class="btn btn-primary" onclick="togglePanel()">添加新目录</button>
                    <button type="button" class="btn btn-primary" id="deleteSelect">批量删除</button>
                </div>
                <div class="box-body">
                    <!-- 下拉面板 start -->
                    <div class="post" id="myPanel" style="display: none">
                        <div class="box no-border">
                            <form class="form-horizontal" id="myForm">
                                <div class="box-header with-border">
                                    <h3 class="box-title">添加目录</h3>
                                </div>
                                <div class="box-body">
                                    <div class="form-group">
                                        <label for="categoryName" class="col-sm-2 control-label">目录名称：</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="categoryName" name="categoryName" placeholder="请填写目录名称">
                                        </div>
                                    </div>
                                </div>
                                <div class="box-footer">
                                    <button type="button" class="btn btn-default" onclick="togglePanel()">取消</button>
                                    <button type="button" class="btn btn-primary pull-right" id="addCategory">确认</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <!-- 下拉面板 end -->
                    <table id="table" class="table table-bordered table-hover table-striped" style="width: 100%">
                        <thead>
                        <tr>
                            <th><label for="checkAll"></label><input type="checkbox" id="checkAll"/></th>
                            <th>序号</th>
                            <th>名称</th>
                            <th>总数</th>
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
    $(function () {
        var dt = $('#table').DataTable({
            serverSide: true,
            processing: true,
            ordering: false,
            ajax: {
                url: '${ctx}/admin/article/category/table.json',
                type: 'POST'
            },
            dom: "<'row'<'col-sm-6'l><'col-sm-6'f>><'row'<'col-sm-12'tr>><'row'<'col-sm-6'i><'col-sm-6'p>>",
            pagingType: 'full_numbers',
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
                    data: "category_id",
                    render: function (data) {
                        return '<input type="checkbox" value="' + data + '"/>';
                    }
                },
                {
                    data: "category_id"
                },
                {
                    data: "category_name"
                },
                {
                    data: "category_count"
                },
                {
                    data: "category_id",
                    render: function (data) {
                        var content = '<div class="btn-group">';
                        content += '    <button type="button" class="btn btn-primary" disabled="disabled" onclick="location=\'${ctx}/admin/article/category/edit/' + data + '\'">编辑</button>';
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

        $("#addCategory").click('click', function () {
            var url = "${ctx}/admin/article/category/new";
            var data = $("#myForm").serialize();
            $.post(url, data, function (result) {
                if (result["success"] === false) {
                    alert("添加失败");
                } else {
                    alert("添加成功");
                    location.reload();
                }
            });
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
                var url = "${ctx}/admin/article/category/deleteSelect";
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
        var url = "${ctx}/admin/article/category/delete/" + obj.value;
        if (confirm("警告：确定删除该目录么？")) {
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

    function togglePanel() {
        $('#myPanel').slideToggle("slow");
    }
</script>
</body>
</html>