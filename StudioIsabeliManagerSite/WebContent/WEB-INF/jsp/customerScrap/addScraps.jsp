<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="studioisabeli.template">
	<tiles:putAttribute name="content">
    
    	<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					Novos Recados
				</h3>
		  	</div>
		  	<div class="panel-body">
    
		    	<form class="bs-example bs-example-form" role="form" action="salvar-recado" method="post" id="salvar-recado-form" name="salvar-recado-form">
					<div class="input-group">
						<span class="input-group-addon">Texto</span>
						<textarea class="form-control" required placeholder="Digite o texto do recado" name="scrap.text" form="salvar-recado-form"></textarea>
					</div>
					
					<table class="table table-hover table-bordered table-condensed">
						<thead>
							<tr>
								<th>
									Alunos que receberão o recado
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${customers}" var="customer">
								<tr>
									<td><input type="checkbox" name="customers[]" value="${customer.customerID}"> ${customer.name}</td>
								</tr>
							</c:forEach>
						</tbody>
						
					</table>
					
					<br/>
					<button class="btn btn-primary" type="submit">Salvar</button>
					
				</form>
			</div>
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
