<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.CartBean"%>
<%@ page import="java.util.ArrayList"%>

<%
	//カート情報
ArrayList<CartBean> cartList = (ArrayList<CartBean>) session.getAttribute("Cart");
CartBean cBean = new CartBean();

//合計金額
int total = (int) session.getAttribute("total");

//税金
int tax = (int) session.getAttribute("tax");
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
<title>確認画面</title>
</head>
<body>
<%@ include file="/jsp/header.jsp" %>
	<div class="frame">
		<div class="text-center">
			<h1>購入してよろしいでしょうか??</h1>
			<div class="container-fluid">
				<table class="table table-striped">
					<tr>
						<td class="col-1" id="column1">商品名</td>
						<td class="col-1" id="column1">単価</td>
						<td class="col-1" id="column1">数量</td>
					</tr>
					<%
						for (int i = 0; i < cartList.size(); i++) {
						cBean = cartList.get(i);
					%>
					<tr>
						<td class="col-1" id="column2"><%=cBean.getProName()%></td>
						<td class="col-1" id="column2"><%=cBean.getProPrice()%></td>
						<td class="col-1" id="column2"><%=cBean.getQuantity()%></td>
					</tr>
					<%
						}
					%>
					<tr>
						<td colspan="2" class="col-1" id="column1">消費税</td>
						<td class="col-1" id="column2"><%=tax%></td>
					</tr>
					<tr>
						<td colspan="2" class="col-1" id="column1">合計金額</td>
						<td class="col-1" id="column2"><%=total%></td>
					</tr>
				</table>
			</div>
			<table align="right">
				<tr>
					<td><form action="/Ecsite/back" method="post">
							<input type="submit" class="btn btn-warning" value="いいえ">
						</form></td>
						<td>&nbsp;</td>
					<td><form action="/Ecsite/confirmation" method="post">
							<input type="submit" class="btn btn-warning" value="はい">
						</form></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>