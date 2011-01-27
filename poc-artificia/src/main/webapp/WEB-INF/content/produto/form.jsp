<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>
<s:actionmessage />
<s:actionerror/>
		<form method="post" action="save.action">
			<table>
				<tr>
					<td colspan="2">Cadastro Produto</td>
				</tr>
				<tr>
					<td>id:</td>
					<td><input type="text" name="produto.id" value="${produto.id }" ${not empty produto ? 'disabled="disabled"':''}" /></td>
				</tr>
				<tr>
					<td>descricao:</td>
					<td><input type="text" name="produto.descricao" value="${produto.descricao }" /></td>
				</tr>
				<tr>
					<td>preco:</td>
					<td><input type="text" name="produto.preco" value="${produto.preco }"/></td>
				</tr>
				<tr>
					<td ><input type="submit" value="salvar" /></td>
				</tr>
			</table>
		</form>
	<table>
		<thead>
			<tr>
				<th>id</th>
				<th>descricao</th>
				<th>preco</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${produtos}" var="produto">
				<tr>
					<td><a href="show.action?id=${produto.id }">${produto.id }</a></td>
					<td>${produto.descricao }</td>
					<td>${produto.preco }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>