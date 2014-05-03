<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="studioisabeli.template">
	<tiles:putAttribute name="content">
	
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="painel-title">Últimos recados de ${customer.name}</h3> <a href="adicionar-recado?customerID=${customer.customerID}" class="btn btn-primary">Novo recado</a>
			</div>
			<div class="panel-body">
				<c:if test="${managerSession.hasMessage}">
					<div class="alert alert-success">
						<h5>${managerSession.message}</h5>
					</div>
				</c:if>
			</div>
			<table class="table table-hover table-bordered table-condensed">
				<thead>
					<tr>
						<th>Data</th>
						<th>Texto</th>
						<th>&nbsp;</th>
						<th>&nbsp;</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${scraps}" var="scrap">
						<tr>
							<td class="date">${scrap.date}</td>
							<td>${scrap.text}</td>
							
							<td><a title="Editar" href="editar-recado?scrap.customerID=${scrap.customerID}&scrap.scrapNumber=${scrap.scrapNumber}"><button type=button class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-pencil"></span></button></a></td>
							<td><a title="Remover" href="remover-recado?scrap.customerID=${scrap.customerID}&scrap.scrapNumber=${scrap.scrapNumber}" onclick="return confirm('Deseja realmente remover');"><button type=button class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-remove-circle"></span></button></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
	</tiles:putAttribute>
	
	<tiles:putAttribute name="scripts">
		<script type="text/javascript">
			$(document).ready(function() {
				$('#menu-home').removeClass("active");
				$('#menu-recados').addClass("active");
			});
		</script>
	</tiles:putAttribute>
	
</tiles:insertDefinition>