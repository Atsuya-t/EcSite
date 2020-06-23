<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.ProductBean" %>

<%
	ProductBean pBean = (ProductBean)session.getAttribute("productDetails");
	String catName = (String)request.getAttribute("catName");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/Ecsite/css/ProductDetails.css">
<link rel="stylesheet" type="text/css" href="/Ecsite/css/Background.css">
<title>商品詳細画面</title>
</head>
<body>
<div class="bk" align="center">
<h1>商品詳細画面</h1>
<img src="<%=pBean.getImg() %>">
<table border="1"  class="border">
<tr><td>商品名</td><td><%= pBean.getProName()%></td></tr>
<tr><td>カテゴリ</td><td><%= catName%></td></tr>
<tr><td>価格</td><td><%= pBean.getProPrice()%></td></tr>
<tr><td>在庫</td><td><%= pBean.getStock()%></td></tr>
<tr><td>商品紹介</td><td><%= pBean.getMsg()%></td></tr>
</table>

<table>
<tr><td>
<form action="/Ecsite/productDetails" method="post">
<select name="quantity">
<% for(int i=1;i<=pBean.getStock();i++){ %>
<option value=<%=i %>><%=i %></option>
<%} %>
</select>
<input type="submit" value="カートへ" align="right">
</form>
</td><td>
<form action="/Ecsite/back" method="post">
<input type="submit" value="戻る">
</form></td></tr>
</table>
</div>
</body>
</html>