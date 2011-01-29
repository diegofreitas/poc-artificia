<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Pedido</title>
</head>
<body>
<ul>
	<li>
		<span>Pedido # ${model.id }</span>
	</li>
	<li>
		<span>Pontuacao # ${model.pontuacao }</span>
	</li>
</ul>

<form action="adicionar-produto.action" method="post">
	<input type="text" value="codigoProduto"/>
	<input type="text" value="quantidade"/>
</form>
<table>
	<thead>
		<tr><th>produto</th><th>qtd</th><th>pontos</th><th>subtotal</th></tr>
	</thead>
	<tbody>
	   <c:forEach items="${model.items}" var="item">
	   		<tr>
				<td>${item.descricaoProduto }</td>
				<td>${item.quantidade }</td>
				<td>${item.pontos }</td>
				<td>${item.total }</td>
			</tr>
	   </c:forEach>
	</tbody>
	<tfoot>
		<tr><th colspan="3">total ${model.total }</th></tr>
	</tfoot>
</table>




</body>
</html>