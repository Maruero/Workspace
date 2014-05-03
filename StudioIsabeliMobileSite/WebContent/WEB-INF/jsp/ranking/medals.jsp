<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="studioisabeli.template">
	
	<tiles:putAttribute name="content">

	
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h4>Suas medalhas <span class="date float-right"></span></h4>
				<p>Ouro: ${goldCount}</p>
				<p>Prata: ${silverCount}</p>
				<p>Bronze: ${bronzeCount}</p>
			</div>
			<div class="panel-body">
				<table class="table table-hover table-bordered table-condensed">
				<thead>
					<tr>
						<th>Data</th>
						<th>Medalha</th>
						<th>Descrição</th>
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
							
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
		</div>

	</tiles:putAttribute>
	
		<tiles:putAttribute name="scripts">
		<script type="text/javascript">
			$(document).ready(function() {
				$('#menu-suas-medalhas').addClass("active");
			});
		</script>
	</tiles:putAttribute>
	
	
</tiles:insertDefinition>