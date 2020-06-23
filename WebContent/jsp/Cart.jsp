<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.CartBean" %>
<%@ page import="java.util.ArrayList" %>

<%
   //カート情報
   ArrayList<CartBean> cartList = (ArrayList<CartBean>)session.getAttribute("Cart");
   CartBean cBean = new CartBean();

   //合計金額
   int total = (int)session.getAttribute("total");

   //税金
   int tax = (int)session.getAttribute("tax");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カート画面</title>
</head>
<body>
<h1>カート画面</h1>
<table border="1">
<tr><td>商品名</td><td>単価</td><td>数量</td></tr>
<%
	for(int i=0;i<cartList.size();i++){
		cBean = cartList.get(i);
%>
<tr><td><%=cBean.getProName() %></td><td><%=cBean.getProPrice() %></td><td><%=cBean.getQuantity() %></td></tr>
<%} %>
<tr><td colspan="2">消費税</td><td><%=tax %></td></tr>
<tr><td colspan="2">合計金額</td><td><%=total %></td></tr>
</table>
<table>
<tr>
<td><form action="/Ecsite/back" method="post">
<input type="submit" value="買い物を続ける">
</form></td>
<td><form action="/Ecsite/cart" method="post">
<input type="submit" value="購入">
</form></td></tr>
</table>

</body>
</html>