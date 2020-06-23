<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="model.CategoryBean" %>
<%@ page import="model.ProductBean" %>
<%@ page import="java.util.ArrayList" %>
<%
   //カテゴリー情報
   ArrayList<CategoryBean> categoryList = (ArrayList<CategoryBean>)session.getAttribute("category");
   CategoryBean cBean = new CategoryBean();

   //検索結果情報
   ArrayList<ProductBean> productList = (ArrayList<ProductBean>)session.getAttribute("product");
   ProductBean pBean = new ProductBean();

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/Ecsite/css/Search.css">
<link rel="stylesheet" type="text/css" href="/Ecsite/css/Background.css">
<title>検索画面</title>
</head>
<body>
<div class="bk" align="center">
<h1>検索画面</h1><br>

<form action="/Ecsite/search" method="post">

<table>
<tr><td colspan="2">
<input type="text" name="keyWord"><br>
</td></tr>
<tr><td>
<select name="categoryId">
<option value=0 selected/>
<% for(int i=0;i<categoryList.size();i++){
	cBean = categoryList.get(i);
%>
<option value=<%=cBean.getCategoryId() %>><%=cBean.getCategoryName() %></option>
<%} %>
</select>
</td><td>
<input type="submit" value="検索"><br>
</td></tr>
</table>
<% String msg= (String)request.getAttribute("err"); %>
<%if(msg!=null){%>
<div class="err"><%=msg %></div>
<%} %>

<% if(productList!=null){%>
<table border="1" class="border">

<tr><th>商品名</th><th>商品価格</th><th></th></tr>

<%
	for(int j=0;j<productList.size();j++){
	pBean = productList.get(j);
%>
<tr>
<td><%=pBean.getProName() %></td>
<td><%=pBean.getProPrice() %></td>
<td><button type="submit" value=<%=pBean.getProCd() %> name="Details">詳細</button></td>
</tr>
<%} %>

</table>
<%} %>

</form>
</div>
</body>
</html>