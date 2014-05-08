<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="studioisabeli.cleanTemplate">
	
	<tiles:putAttribute name="content">
	
		<div class="jumbotron">
		  <h1>Studio Isabeli Vilhena</h1>
		  <p>Atividades Físicas Personalizadas</p>
		</div>
		
	</tiles:putAttribute>
	
	<tiles:putAttribute name="scripts">
		<script type="text/javascript">
			$(document).ready(function() {
				setTimeout(function() {
					window.location = 'tip';
				}, "4000");
			});
		</script>
	</tiles:putAttribute>
	
</tiles:insertDefinition>