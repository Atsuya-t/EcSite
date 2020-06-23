<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>完了画面</title>
</head>
<body>
<h1>お買い上げありがとうございました！</h1>

<table>
<tr>
<td>
<form action="/Ecsite/back" method="post"><input type="submit" value="買い物を続ける"></form>
</td>
<td>
<form action="/Ecsite/logout" method="post"><input type="submit" value="ログアウト"></form>
</td>
</tr>
</table>
</body>
</html>