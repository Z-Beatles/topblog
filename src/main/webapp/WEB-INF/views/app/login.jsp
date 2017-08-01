<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!DOCTYPE html>
<html lang="zh">
<head>
<%@ include file="/WEB-INF/views/admin/snippet_header_admin.jsp"%>
</head>
<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<a href="${ctx}"><b>Topblog</b><small> User Login</small></a>
		</div>
		<div class="login-box-body">
			<p class="login-box-msg">用户登录</p>
			<form action="/login" method="post">
				<input type="hidden" name="loginType" value="user">
				<div class="form-group has-feedback">
					<input type="text" class="form-control" placeholder="帐号" name="username">
                    <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" class="form-control" placeholder="密码"
						name="password"> <span
						class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="row">
					<div class="col-xs-8">
						<div class="checkbox icheck">
							<label><input type="checkbox" name="rememberMe">
								记住我</label>
						</div>
					</div>
					<div class="col-xs-4">
						<button type="submit" class="btn btn-primary btn-block btn-flat">
							登录</button>
					</div>
				</div>
			</form>

			<div class="social-auth-links text-center">
				<p>-- OR --</p>
				<a href="/login/qq"
					class="btn btn-block btn-social btn-facebook btn-flat"> <i
					class="fa fa-qq"></i> 使用 QQ 帐号登录
				</a> <a href="/login/weixin"
					class="btn btn-block btn-social btn-google btn-flat"> <i
					class="fa fa-weixin"></i> 使用 微信 帐号登录
				</a>
			</div>
			<a href="/forget">忘记密码？</a><br> <a href="/register"
				class="text-center">注册新账号</a>
		</div>
	</div>

	<script>
		$(function() {
			$('input').iCheck({
				checkboxClass : 'icheckbox_square-blue',
				radioClass : 'iradio_square-blue',
				increaseArea : '20%' // optional
			});
		});
	</script>
</body>
</html>
