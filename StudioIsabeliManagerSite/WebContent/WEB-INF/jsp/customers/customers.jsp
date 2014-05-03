<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="studioisabeli.template">
	<tiles:putAttribute name="content">
	
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="painel-title">Alunos cadastrados</h3> <a href="adicionar-aluno" class="btn btn-primary">Novo aluno</a>
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
						<th>Nome</th>
						<th>&nbsp;</th>
						<th>&nbsp;</th>
						<th>&nbsp;</th>
						<th>&nbsp;</th>
						<th>&nbsp;</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${customers}" var="customer">
						<tr>
							<td>${customer.name}</td>
							
							<td><a title="Recados" href="recados-do-aluno?customerID=${customer.customerID}"><button class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-envelope"></span></button></a></td>
							<td><a title="Agenda" href="agenda-do-aluno?customerID=${customer.customerID}"><button class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-book"></span></button></a></td>
							<td><a title="Metas" href="marcas-do-aluno?customerID=${customer.customerID}"><button class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-thumbs-up"></span></button></a></td>
							<td><a title="Editar" href="editar-aluno?customerID=${customer.customerID}"><button type=button class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-pencil"></span></button></a></td>
							<td><a title="Remover" href="remover-aluno?customerID=${customer.customerID}" onclick="return confirm('Deseja realmente remover');"><button type=button class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-remove-circle"></span></button></a></td>
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
				$('#menu-alunos').addClass("active");
			});
		</script>
	</tiles:putAttribute>
	
</tiles:insertDefinition>