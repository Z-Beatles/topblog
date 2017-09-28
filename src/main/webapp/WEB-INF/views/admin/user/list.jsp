<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<!DOCTYPE html>
<html lang="zh">
<head>
    <%@ include file="/WEB-INF/views/admin/common_head.jsp" %>
    <script type="text/javascript">

        $(document).ready(function () {
            var dt = $('#table').DataTable({
                serverSide: true,
                processing: true,
                ajax: {url: '${ctx}/admin/user/list.json', type: 'post'},
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
                        title:'<input type="checkbox" id="checkAll"/>',
                        data: "id",
                        render: function (data) {
                            var content = '<input type="checkbox"value="' + data + '"/>';
                            return content;
                        }
                    },
                    {
                        title:"ID",
                        data: "id"
                    },
                    {
                        title:"头像",
                        data: "avatar",
                        render: function (data) {
                            var imageUrl = (data != null) ? data : ("${ctxPath}/static/image/user.jpg");
                            var content = '<image src=' + imageUrl + ' style="width:30px;height:30px"></image>';
                            return content;
                        }
                    },
                    {
                        title:"用户名",
                        data: "username"
                    },
                    {
                        title:"昵称",
                        data: "nickname"
                    },
                    {
                        title:"电话",
                        data: "mobile"
                    },
                    {
                        title:"邮箱",
                        data: "email"
                    },
                    {
                        title:"禁用",
                        data: "disabled",
                        render: function (data) {
                            var content = '<div class="btn-group">';
                            if(data){
                                content += '    <button type="button" class="btn btn-primary">启用</button>';
                            }else {
                                content += '    <button type="button" class="btn btn-primary">禁用</button>';
                            }
                            return content;
                        }
                    },
                    {
                        title:"锁定",
                        data: "locked",
                        render: function (data) {
                            var content = '<div class="btn-group">';
                            if(data){
                                content += '    <button type="button" class="btn btn-primary">解锁</button>';
                            }else {
                                content += '    <button type="button" class="btn btn-primary">锁定</button>';
                            }
                            return content;
                        }
                    },
                    {
                        title:"操作",
                        data : 'id',
                        render: function (data) {
                            var content = '<div class="btn-group">';
                            content += '    <button type="button" class="btn btn-primary" onclick="location=\'${ctx}/admin/user/delete/' + data + '\'">编辑</button>';
                            content += '    <button type="button" class="btn btn-primary" value="' + data + '" onclick="deleteUser(this)" >删除</button>';
                            content += '</div>';
                            return content;
                        }
                    }
                ]
            });
            $('input[table-column-selector]').each(function (index, target) {
                $(target).change(function () {
                    var enable = $(this)[0].checked;
                    var index = $(this).closest('th').index();
                    $(this).closest('table').find('tr td:nth-child(1) :input').each(function (i, n) {
                        n.checked = enable;
                    });
                });
            });
            $("#checkAll").click(function () {
                if (this.checked) {
                    $('table tr td input[type=checkbox]').prop('checked', true);
                } else {
                    $('table tr td input[type=checkbox]').prop('checked', false);
                }
            });
        });
        function deleteUser(obj) {
            var url = "${ctx}/admin/user/deleteUser";
            if (confirm("警告：确定删除该用户么？")) {
                $.get(url,{'id':obj.value},function (result) {
                    if (result["success"] === false) {
                        alert(result);
                    } else {
                        alert("删除成功！");
                        location.reload();
                    }
                },'json');
            }
        }
    </script>
</head>
<body class="hold-transition skin-purple sidebar-mini">
<div class="wrapper">
    <jsp:include page="/WEB-INF/views/admin/header.jsp"/>
    <jsp:include page="/WEB-INF/views/admin/aside.jsp">
        <jsp:param value="user" name="pageCatalog"/>
        <jsp:param value="user_list" name="pageMenu"/>
    </jsp:include>
    <div class="content-wrapper" style="min-height: 1126px;">
        <section class="content-header">
            <h1>
                用户
                <small>列表</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="${ctx}/admin"><i class="fa fa-dashboard"></i>首页</a></li>
                <li>用户</li>
                <li class="active"><a href="${ctx}/admin/user/list">所有用户</a></li>
            </ol>
        </section>
        <section class="content">
            <div class="box">
                <div class="box-header with-border">
                    <a class="btn btn-primary" href="${ctx}/admin/user/add">添加用户</a>
                </div>
                <div class="box-body">
                    <table id="table" class="table table-bordered table-hover table-striped">
                    </table>
                </div>
            </div>
        </section>
    </div>
    <%@ include file="/WEB-INF/views/admin/footer.jsp" %>
</div>
</body>
</html>