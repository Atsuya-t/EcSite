<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/Ecsite/css/Login.css">
<title>ログイン画面</title>
</head>
<body>
	<%
		String msg = (String) request.getAttribute("err");
	%>
	<%
		if (msg != null) {
	%>
	<div class="err"><%=msg%></div>
	<%
		}
	%>

	<div class="body"></div>
	<div class="grad"></div>
	<div class="header">
		<div>
			<b> TKO's<span>ECsite</span>
			</b>
		</div>
	</div>
	<br>
	<form action="/Ecsite/login" method="post">
		<div class="login">
			<input type="text" placeholder="username" name="loginCd"><br>
			<input type="password" placeholder="password" name="loginPw"><br>
			<input type="submit" value="Login">
		</div>
	</form>
</body>
</html>