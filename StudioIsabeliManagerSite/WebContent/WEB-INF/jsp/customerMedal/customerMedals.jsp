<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="studioisabeli.template">
	<tiles:putAttribute name="content">
	
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="painel-title">Medalhas de ${customer.name}</h3> <a href="adicionar-medalha-do-aluno?customerID=${customer.customerID}" class="btn btn-primary">Nova medalha</a>
			</div>
			<div class="panel-body">
				<c:if test="${managerSession.message != null}">
					<div class="alert alert-success">
						<h5>${managerSession.message}</h5>
					</div>
				</c:if>
			</div>
			<table class="table table-hover table-bordered table-condensed">
				<thead>
					<tr>
						<th>Data</th>
						<th>Medalha</th>
						<th>Descrição</th>
						<th>&nbsp;</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${medals}" var="medal">
						<tr>
							<td class="date">${medal.date}</td>
							<c:choose>
								<c:when test="${medal.medal.type == 'GOLD'}">
									<td>Ouro</td>
								</c:when>
								<c:when test="${medal.medal.type == 'SILVER'}">
									<td>Prata</td>
								</c:when>
								<c:when test="${medal.medal.type == 'BRONZE'}">
									<td>Bronze</td>
								</c:when>
							</c:choose>
							
							<td>${medal.description}</td>
							
							<td><a title="Editar" href="editar-medalha-do-aluno?medal.customerID=${medal.customerID}&medal.medalID=${medal.medalID}"><button type=button class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-pencil"></span></button></a></td>
							
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
				$('#menu-ranking').addClass("active");
			});
		</script>
	</tiles:putAttribute>
	
</tiles:insertDefinition>