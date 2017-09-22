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
                    <!-- 模态触发按钮 -->
                    <button type="button" class="btn btn-primary" id="toggleModal">添加新目录</button>
                </div>
                <!-- 模态框-->
                <div class="modal fade" id="myModal">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" onclick="window.location.reload();">
                                    <span>&times;</span></button>
                                <h4 class="modal-title">添加目录</h4>
                            </div>
                            <div class="modal-body">
                                <form class="form-horizontal" id="category">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">目录名称：</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" name="categoryName" placeholder="请输入目录名称">
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <span id="tip" style="display: none;color:red;font-size:16px;">添加成功！</span>
                                <button type="button" class="btn btn-default" onclick="window.location.reload();">关闭</button>
                                <button type="button" class="btn btn-primary" id="newCategory">添加</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="box-body">
                    <table id="table" class="table table-bordered table-hover table-striped">
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
            searching: false,
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
                        content += '    <button type="button" class="btn btn-primary" onclick="location=\'${ctx}/admin/article/edit/' + data + '\'">编辑</button>';
                        content += '    <button type="button" class="btn btn-primary" value="' + data + '" onclick="deleteArticle(this)" >删除</button>';
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
                var url = "/setup/dictionary/market/list/deleteSelect";
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

        $("#toggleModal").click(function () {
            $("#myModal").modal({
                show: true,//显示弹出层
                backdrop: 'static',//禁止位置关闭
                keyboard: false//关闭键盘事件
            });
        });
        $("#newCategory").one('click', function () {
            var url = "/article/category/new";
            var data = $("#category").serialize();
            $.post(url, data, function (result) {
                if (result) {
                    $("#tip").fadeIn("slow");
                    $("#tip").fadeOut("slow");
                    $("#tip").fadeIn("slow");
                    $("#tip").fadeOut("slow");
                    $("#tip").fadeIn("slow");
                    $("#tip").fadeOut("slow");
                }
            });
        });
    });
</script>
</body>
</html>