<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Studio Isabeli Vilhena</title>
</head>
<body>
	<form action="token" method="POST">
		${message}
		<br>
		Token: <input type="text" name="token"/>
		<input type="submit" value="entrar"/>
	</form>
</body>
</html>