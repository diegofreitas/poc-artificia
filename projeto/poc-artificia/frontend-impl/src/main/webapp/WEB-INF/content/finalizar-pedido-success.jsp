<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Finalizacao do Pedido</title>
</head>
<body>
<h1>Pedido Finalizado com sucesso</h1>
<ul>
	<li>
		<span>Pedido # ${model.id }</span>
	</li>
	<li>
		<span>Pontuacao # ${model.pontuacao }</span>
	</li>
	<li>
		<span>total ${model.total }</span>
	</li>
	<li>
		<span>situacao ${model.situacao }</span>
	</li>
	
</ul>


<a href="index.action">voltar</a>


</body>
</html>