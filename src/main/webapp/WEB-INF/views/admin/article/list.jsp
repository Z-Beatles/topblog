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
            <jsp:param value="article_list" name="pageMenu"/>
        </jsp:include>
        <div class="content-wrapper" style="min-height: 1126px;">
            <section class="content-header">
                <h1>
                                                   文章<small>列表</small>
                </h1>
                <ol class="breadcrumb">
                    <li><a href="${ctx}/"><i class="fa fa-dashboard"></i>首页</a></li>
                    <li>文章</li>
                    <li class="active"><a href="${ctx}/article/list">所有文章</a></li>
                </ol>
            </section>
            <section class="content">
                <div class="box">
                    <div class="box-header with-border">
                        <a class="btn btn-primary" href="${ctx}/article/category/add">写文章</a>
                    </div>
                    <div class="box-body">
                        <table id="table" class="table table-bordered table-hover table-striped">
                            <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>标题</th>
                                    <th>作者</th>
                                    <th>分类目录</th>
                                    <th>标签</th>
                                    <th>发布日期</th>
                                    <th>文章内容</th>
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
    <script type="text/javascript">
        $(document).ready(function(){
            var dt=$('#table').DataTable({
                serverSide:true,
                processing:true,
                searching: false,
                ordering:  false,
                ajax:{url:'${ctx}/article/list/table.json',type:'POST'},
                dom:"<'row'<'col-sm-6'l><'col-sm-6'f>><'row'<'col-sm-12'tr>><'row'<'col-sm-6'i><'col-sm-6'p>>",
                pagingType:'full_numbers',
                language:{
                    "search":"搜索:",
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
                    },
                },
                columns:[
                    {data:"articleId"},
                    {data:"articleTitle"},
                    {data:"articleAuthor"},
                    {data:"articleCategory"},
                    {data:"articleTag"},
                    {data:"articleTime"},
                    {data:"articleContent"}
                ]
            });
        });
    </script>
</body>
</html>