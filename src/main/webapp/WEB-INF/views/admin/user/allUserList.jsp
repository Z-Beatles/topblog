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
        //删除信息
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
        //验证输入内容
        function checkValue(type,param){

            var mobile = /(^0{0,1}1[3|4|5|6|7|8|9][0-9]{9}$)/;
            var email = /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/;
            switch (type)
            {
                case 1:
                   if($.trim(param)==null || $.trim(param)==""){
                       return false;
                   }else {
                       return true;
                   }
                    break
               case 2:
                   return mobile.test($.trim(param));
                    break;
               case 3:
                   return email.test($.trim(param));
                   break;
               case 4:
                   if($.trim(param)==null || $.trim(param)==""){
                       return false;
                   }else {
                       return true;
                   }
                   break;

            }
        }

        //保存信息
        function saveUserMessage() {
            if(!checkValue(1,$("#username").val())){
                alert("用户名不能为空！");
                return;
            };

            if(!checkValue(2,$("#mobile").val())){
                alert("请输入正确的手机号！");
                return;
            };

            if(!checkValue(3,$("#email").val())){
                alert("请输入正确的邮箱！");
                return;
            };
            $.post("${ctx}/admin/user/addUser",$("#myForm").serialize(),function (result) {
                 console.log(result);
                }
            );
            togglePanel();
            location.reload();
        }

        function togglePanel() {
            $('#myPanel').slideToggle("slow");
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
                    <a class="btn btn-primary" onclick="togglePanel()">添加用户</a>
                </div>

                <div class="box-body">
                    <!-- 下拉面板 start -->
                    <div class="post" id="myPanel" style="display: none">
                        <div class="box no-border">
                            <form class="form-horizontal" id="myForm">
                                <div class="box-header with-border">
                                    <h3 class="box-title">用户信息</h3>
                                </div>
                                <div class="box-body">
                                    <div class="form-group">
                                        <label for="username" class="col-sm-1 control-label">用户名：</label>
                                        <div class="col-sm-2">
                                            <input type="text" class="form-control" id="username" name="username" placeholder="请填写用户名称(必填)">
                                        </div>


                                        <label for="nickname" class="col-sm-1 control-label">昵称：</label>
                                        <div class="col-sm-2">
                                            <input type="text" class="form-control" id="nickname" name="nickname" placeholder="请填写昵称(非必填)">
                                        </div>


                                        <label for="mobile" class="col-sm-1 control-label">电话：</label>
                                        <div class="col-sm-2">
                                            <input type="text" class="form-control" id="mobile" name="mobile" placeholder="请填写电话(必填)">
                                        </div>


                                        <label for="email" class="col-sm-1 control-label">邮箱：</label>
                                        <div class="col-sm-2">
                                            <input type="text" class="form-control" id="email" name="email" placeholder="请填写邮箱(必填)">
                                        </div>
                                    </div>

                                </div>
                                <div class="box-footer">
                                    <button type="button" class="btn btn-primary pull-right" onclick="togglePanel()">取消</button>
                                    <button type="button" class="btn btn-primary pull-right" id="addCategory" onclick="saveUserMessage()">确认</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <!-- 下拉面板 end -->
                    <%--数据表格--%>
                    <table id="table" class="table table-bordered table-hover table-striped"></table>
                </div>
            </div>
        </section>
    </div>
    <%@ include file="/WEB-INF/views/admin/footer.jsp" %>
</div>
</body>
</html>