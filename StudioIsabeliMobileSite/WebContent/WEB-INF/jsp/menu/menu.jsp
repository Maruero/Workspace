<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="studioisabeli.template">
	
	<tiles:putAttribute name="content">
		<div class="jumbotron">
		  <h2>Sua próxima aula é</h2>
		  <p>${nextClass}</p>
		</div>
	</tiles:putAttribute>
	
</tiles:insertDefinition>