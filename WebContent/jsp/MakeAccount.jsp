<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/Ecsite/css/bootstrap.css">
<link rel="stylesheet" href="/Ecsite/css/frame.css">
<link rel="stylesheet" href="/Ecsite/css/btn.css">
<script type="text/javascript" src="/Ecsite/js/jquery-3.5.1.js"></script>
<script type="text/javascript" src="/Ecsite/js/bootstrap.js"></script>
<title>アカウント作成画面</title>
</head>
<body>
	<c:if test="${ err1 != null }">
		<div class="alert alert-warning" role="alert">
			<c:out value="${ err1}" />
		</div>
	</c:if>
	<c:if test="${ err2.get('nameErr') != null }">
		<div class="alert alert-warning" role="alert">
			<c:out value="${ err2.get('nameErr') }" />
		</div>
	</c:if>
	<c:if test="${ err2.get('complete') != null }">
		<div class="alert alert-success" role="alert">
			<c:out value="${ err2.get('complete') }" />
		</div>
	</c:if>



		 <div class="frame">
		<h1 class="text-center">
			<b> アカウント作成</b> <br>
		</h1>
		<form action="/Ecsite/makeAccount" method="post">
			<div class="row">
				<label for="loginCd">UserName:</label> <input type="text"
					name="loginCd" class="form-control" maxlength="50">
			</div>
			<div class="row">
				<label for="loginPw">Password:</label> <input type="password"
					name="loginPw" class="form-control">
			</div>
			<br>
			<div class="pull-right">
				<input type="submit" class="btn btn-warning" value="登録する">
				<button type="submit" class="btn btn-warning" name="back"
					value="back">ログイン画面に戻る</button>
			</div>
		</form>
	</div>
</body>
</html>