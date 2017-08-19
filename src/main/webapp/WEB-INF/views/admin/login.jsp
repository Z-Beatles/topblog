<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
	<%@ include file="/WEB-INF/views/admin/common_head_admin.jsp"%>
	<link rel="stylesheet" href="${ctx}/static/plugin/bootstrapvalidator-0.5.3/css/bootstrapValidator.min.css">
	<link rel="stylesheet" href="${ctx}/static/plugin/icheck-1.0.1/skins/square/blue.css">
</head>
<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<a href="${ctx}"><b>Topblog</b><Small> Admin</Small></a>
		</div>
		<div class="login-box-body">
			<p class="login-box-msg">-- 管理员登录 --</p>
			<form action="/login" method="post" id="loginform">
				<input type="hidden" name="loginType" value="admin">
				<c:if test="${errormsg != null}">
					<div class="alert alert-danger">${errormsg}</div>
				</c:if>
				<div class="form-group has-feedback">
					<input type="text" class="form-control" placeholder="帐号" name="username">
                    <span class="glyphicon glyphicon-user form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" class="form-control" placeholder="密码" name="password">
					<span class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="row">
					<div class="col-xs-8">
						<div class="checkbox icheck">
							<label><input type="checkbox" name="rememberMe">记住我</label>
						</div>
					</div>
					<div class="col-xs-4">
						<button type="submit" class="btn btn-primary btn-block btn-flat">登录</button>
					</div>
				</div>
			</form>
			<div class="social-auth-links text-center">
				<p>-- OR --</p>
				<a href="#" class="btn btn-block btn-social btn-facebook btn-flat">
					<i class="fa fa-envelope"></i> 使用 邮箱 登录
				</a> <a href="#" class="btn btn-block btn-social btn-google btn-flat">
					<i class="fa fa-phone"></i> 使用 手机号 登录
				</a>
			</div>
			<a href="/forget">忘记密码？</a>
		</div>
	</div>

	<script src="${ctx}/static/plugin/bootstrapvalidator-0.5.3/js/bootstrapValidator.js"></script>
    <script src="${ctx}/static/plugin/icheck-1.0.1/icheck.min.js"></script>
    <script>
        $(document).ready(function() {
            $('.icheck').iCheck({
                checkboxClass : 'icheckbox_square-blue',
                radioClass : 'iradio_square-blue',
                increaseArea : '50%' // optional
            });
            $("#loginform").bootstrapValidator({
                fields : {
                    username : {
                        validators : {
                            notEmpty : {
                                message : '账号不能为空!'
                            },
                        },
                    },
                    password : {
                        validators : {
                            notEmpty : {
                                message : '密码不能为空!'
                            },
                        },
                    },
                }
            });
        });
    </script>
</body>
</html>