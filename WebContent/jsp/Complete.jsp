<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/Ecsite/css/bootstrap.css">
<link rel="stylesheet" href="/Ecsite/css/frame.css">
<link rel="stylesheet" href="/Ecsite/css/btn.css">
<link rel="stylesheet" href="/Ecsite/css/table.css">
<script type="text/javascript" src="/Ecsite/js/jquery-3.5.1.js"></script>
<script type="text/javascript" src="/Ecsite/js/bootstrap.js"></script>
<title>完了画面</title>
</head>
<body>
	<div class="frame">
		<div class="text-center">
			<h1>お買い上げありがとうございました！</h1>

			<table align="center">
				<tr>
					<td>
						<form action="/Ecsite/back" method="post">
							<input type="submit" class="btn btn-warning" value="買い物を続ける">
						</form>
					</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>
						<form action="/Ecsite/logout" method="post">
							<input type="submit" class="btn btn-warning" value="ログアウト">
						</form>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>