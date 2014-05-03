<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="studioisabeli.template">
	<tiles:putAttribute name="content">
	
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="painel-title">Ranking</h3>
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
						<th>Posição</th>
						<th>Nome</th>
						<th>Ouro</th>
						<th>Prata</th>
						<th>Bronze</th>
						<th>&nbsp</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ranking}" var="customer" varStatus="position" >
						<tr>
							<td>${position.index +1}º</td>
							<td>${customer.customer.name}</td>
							
							<td>${customer.goldCount}</td>
							<td>${customer.silverCount}</td>
							<td>${customer.bronzeCount}</td>
							
							<td><a title="Medalhas" href="medalhas-do-aluno?customerID=${customer.customer.customerID}"><button type=button class="btn btn-primary btn-sm">Medalhas</button></a></td>
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