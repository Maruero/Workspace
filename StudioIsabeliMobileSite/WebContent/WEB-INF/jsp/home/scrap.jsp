<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Studio Isabeli Vilhena</title>
</head>
<body>
	${scrap.date} <br>
	${scrap.text} <br>
	
	<c:choose>
		<c:when test="${scrap.previousScrapNumber != 0 }">
			<a href="scrap?scrapNumber=${scrap.previousScrapNumber}"> Anterior </a>
		</c:when>
		<c:otherwise>
			 Anterior 
		</c:otherwise>
	</c:choose>
	
	<c:choose>
		<c:when test="${scrap.nextScrapNumber != 0 }">
			<a href="scrap?scrapNumber=${scrap.nextScrapNumber}"> Próximo </a>
		</c:when>
		<c:otherwise>
			 Próximo 
		</c:otherwise>
	</c:choose>
	
	<br>
	<a href="menu"> Avançar </a>
	
</body>
</html>