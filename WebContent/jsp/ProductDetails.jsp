<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bean.ProductBean"%>

<%
	ProductBean pBean = (ProductBean) session.getAttribute("productDetails");
String catName = (String) request.getAttribute("catName");
%>
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
<title>商品詳細画面</title>
</head>
<body>
<%@ include file="/jsp/header.jsp" %>
	<div class="frame">
		<div class="text-center">
			<h1>
				<b>商品詳細画面</b>
			</h1>
			<img src="<%=pBean.getImg()%>"><br> <br>
			<div class="container-fluid">
				<table class="table table-striped">
					<tr>
						<td class="col-1" id="column1"><b>商品名</b></td>
						<td class="col-1" id="column2"><%=pBean.getProName()%></td>
					</tr>
					<tr>
						<td class="col-1" id="column1"><b>カテゴリ</b></td>
						<td class="col-1" id="column2"><%=catName%></td>
					</tr>
					<tr>
						<td class="col-1" id="column1"><b>価格</b></td>
						<td class="col-1" id="column2">¥<%=String.format("%,d", pBean.getProPrice())%></td>
					</tr>
					<tr>
						<td class="col-1" id="column1"><b>在庫</b></td>
						<td class="col-1" id="column2"><%=pBean.getStock()%></td>
					</tr>
					<tr>
						<td class="col-1" id="column1"><b>商品紹介</b></td>
						<td class="col-1" id="column2"><%=pBean.getMsg()%></td>
					</tr>
				</table>
			</div>
			<br>
				<table align="right">
					<tr>

						<td>
							<form action="/Ecsite/productDetails" method="post"
								class="form-inline">
								<input type="number" name="quantity" value="0" min="1"
									max=<%=pBean.getStock()%> class="form-group"> <input
									type="submit" class="btn btn-warning" value="カートへ">
							</form>
						</td>
					<td>&nbsp;</td>
						<td>
							<form action="/Ecsite/back" method="post" class="form-inline">
								<input type="submit" class="btn btn-warning" value="戻る">
							</form>
						</td>
					</tr>
				</table>
		</div>
	</div>
</body>
</html>