<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="model.CategoryBean"%>
<%@ page import="model.ProductBean"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%
	//カテゴリー情報
ArrayList<CategoryBean> categoryList = (ArrayList<CategoryBean>) session.getAttribute("category");
CategoryBean cBean = new CategoryBean();

//検索結果情報
ArrayList<ProductBean> productList = (ArrayList<ProductBean>) session.getAttribute("product");
ProductBean pBean = new ProductBean();
%>

<!DOCTYPE html>
<html>
<head>
<style>
p.gray{ color:gray; }

td.gray{
	background-color:#dcdcdc;
	border-style: solid;
	border-color: #f27200;
	border-width: 1px;
}
</style>
<meta charset="UTF-8">
<link rel="stylesheet" href="/Ecsite/css/bootstrap.css">
<link rel="stylesheet" href="/Ecsite/css/frame.css">
<link rel="stylesheet" href="/Ecsite/css/btn.css">
<link rel="stylesheet" href="/Ecsite/css/table.css">
<script type="text/javascript" src="/Ecsite/js/jquery-3.5.1.js"></script>
<script type="text/javascript" src="/Ecsite/js/bootstrap.js"></script>
<title>検索画面</title>
</head>

<body>
<%@ include file="/jsp/header.jsp" %>
<div class="frame">
<h1 class="text-center"><b>検索画面</b></h1><br>
<form action="/Ecsite/search" method="post">
<div class="row">
<table align="center">
<tr><td>
<select id="select1a" class="form-control" name="categoryId">
<option value=0 selected>すべて</option>
<%
for (int i = 0; i < categoryList.size(); i++) {
cBean = categoryList.get(i);
%>
<option value=<%=cBean.getCategoryId()%>><%=cBean.getCategoryName()%></option>
<%
}
%>
</select>
</td><td>
<input type="text" name="keyWord" class="form-control">
</td><td>
<input type="submit" class="btn btn-warning" value="検索">
</td>
</table>
<br>
</div>
</form>
<br>
<%
String msg = (String) request.getAttribute("err");
%>
<%
if (msg != null) {
%>
<div class="alert alert-warning" role="alert">
<%=msg%>
</div>
<%
}
%>
<%
if (productList != null) {
%>
<br>
<form name="form1">
<div class="container-fluid">
<table class="table table-striped">
<tr>
<td class="col-1" id="column1"><b>商品名</b></td>
<td class="col-1" id="column1"><b>商品価格</b></td>
<td class="col-1" id="column1"><b>在庫</b></td>
</tr>
<%
	for (int j = 0; j < productList.size(); j++) {
	pBean = productList.get(j);
%>
<% if (pBean.getStock() <= 0) { %>

<tr>
<td class="gray"><p><%=pBean.getProName()%></p></td>
<td class="gray">¥<%=String.format("%,d", pBean.getProPrice())%></td>
<td class="gray"><p style="color: red"><b>SOLD OUT</b></p></td>
</tr>

<% } else { %>

<tr>
<td class="col-1" id="column2"><a href="/Ecsite/search?form1=<%=pBean.getProCd()%>"><%=pBean.getProName()%></a></td>
<td class="col-1" id="column2">¥<%=String.format("%,d", pBean.getProPrice())%></td>
<td class="col-1" id="column2"><%=String.format("%,d", pBean.getStock())%></td></tr>
<%
	}
	}
%>
</table>
</div>
</form>
<%
	}
%>
</div>
</body>
</html>