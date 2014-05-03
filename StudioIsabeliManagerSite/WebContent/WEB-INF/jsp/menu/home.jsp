<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="studioisabeli.template">
	<tiles:putAttribute name="content">
	
		<c:if test="${managerSession.hasMessage}">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="alert alert-success">
						<h5>${managerSession.message}</h5>
					</div>
				</div>
			</div>
		</c:if>
		&nbsp;
		
	</tiles:putAttribute>
</tiles:insertDefinition>