<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="studioisabeli.template">
	<tiles:putAttribute name="content">
	
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="painel-title">Ranking</h3>
			</div>
			<div class="panel-body">
				<h4>
					Atualmente sua visibilidade é
					<c:choose>
						<c:when test="${visible}">
							visível.
						</c:when>
						<c:otherwise>
							invisível.
						</c:otherwise>
					</c:choose>
				</h4>
				<h5>
					Deseja mudar sua visibilidade para
					<c:choose>
						
						<c:when test="${visible}">
							invisível.
						</c:when>
						<c:otherwise>
							visível
						</c:otherwise>
					</c:choose>
					<button type="button" class="btn btn-primary" onClick="toggleVisibility();">Clique aqui!</button>
				</h5>
			</div>
			<table class="table table-hover table-bordered table-condensed">
				<thead>
					<tr>
						<th>Posição</th>
						<th>Nome</th>
						<th>Ouro</th>
						<th>Prata</th>
						<th>Bronze</th>
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
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
	</tiles:putAttribute>
	
	<tiles:putAttribute name="scripts">
		<script type="text/javascript">
			$(document).ready(function() {
				$('#menu-ranking').addClass("active");
			});
			
			function toggleVisibility(){
				window.location = "mudar-minha-visibilidade";
			}
		</script>
	</tiles:putAttribute>
	
</tiles:insertDefinition>