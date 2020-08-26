<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.header {
	border: solid 1px;
	padding: 10px;
	width: 50%;
	margin: 0px 25%;
	background-color: #db7093;
}

#hbutton {
	display: block;
	margin: 0 0 0 auto;
}
</style>
<link rel="stylesheet" href="/Ecsite/css/bootstrap.css">
<script type="text/javascript" src="/Ecsite/js/jquery-3.5.1.js"></script>
<script type="text/javascript" src="/Ecsite/js/bootstrap.js"></script>
</head>
<body>
	<form action="/Ecsite/logout" method="post">
		<div class="header">
			<input type="submit" value="ログアウト" class="btn btn-default"
				id="hbutton">
		</div>
	</form>
</body>
</html>