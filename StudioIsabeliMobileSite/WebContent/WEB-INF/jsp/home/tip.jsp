<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Studio Isabeli Vilhena</title>
</head>
<body>
	${tip.date} <br>
	${tip.text} <br>

	<c:choose>
		<c:when test="${tip.previousTipID != 0 }">
			<a href="tip?tipID=${tip.previousTipID}"> Anterior </a>
		</c:when>
		<c:otherwise>
			 Anterior 
		</c:otherwise>
	</c:choose>
	
	<c:choose>
		<c:when test="${tip.nextTipID != 0 }">
			<a href="tip?tipID=${tip.nextTipID}"> Próximo </a>
		</c:when>
		<c:otherwise>
			 Próximo 
		</c:otherwise>
	</c:choose>
	
	<br>
	<a href="scrap"> avançar </a>
	
</body>
</html>