<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="studioisabeli.cleanTemplate">
	
	<tiles:putAttribute name="content">
	
		
	
		<form class="form-signin" role="form" action="token" method="post">
		    <h2 class="form-signin-heading">Digite o seu token!</h2>
		    <input type="text" class="form-control" name="token" placeholder="Token" required autofocus>
		    
		    <br/>
		    
		    <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
	    </form>
	    
	    <c:choose>
		    	<c:when test="${message != null}">
		    		<div class="alert alert-danger">
		    			<h4>${message}</h4>
		    			<p>Solicite um válido ao seu personal.</p>
					</div>
		    	</c:when>
		    	<c:otherwise>
		    		<br>
		    	</c:otherwise>
		    </c:choose>
		
	</tiles:putAttribute>

	
</tiles:insertDefinition>