<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="studioisabeli.template">
	<tiles:putAttribute name="content">
    
    	<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					<c:if test="${medal.medalID == null || medal.medalID == 0 }">
						Nova&nbsp;
					</c:if>
					Medalha de ${customer.name}
				</h3>
		  	</div>
		  	<div class="panel-body">
    
		    	<form class="bs-example bs-example-form" role="form" action="salvar-medalha-do-aluno" method="post" id="salvar-aluno-form">
		    		<input type="hidden" id="medalID" name="medal.medalID" value="${medal.medalID}"/>
		    		<input type="hidden" id="customerID" name="medal.customerID" value="${customer.customerID}"/>
					<div class="input-group">
						<span class="input-group-addon">Medalha</span>
						<select class="form-control" required autofocus name="medal.medal.type">
							<option value="GOLD" id="medal-GOLD">Ouro</option>
							<option value="SILVER" id="medal-SILVER">Prata</option>
							<option value="BRONZE" id="medal-BRONZE">Bronze</option>
						</select>
					</div>
					<div class="input-group">
						<span class="input-group-addon">Descrição</span>
						<input type="text" class="form-control" required autofocus name="medal.description" value="${medal.description}"/>
					</div>
					
					<br/>
					<span class="input-group-btn">
						<button class="btn btn-default" type="submit">Salvar</button>
					</span>
				</form>
			</div>
		</div>

	</tiles:putAttribute>
	
	<tiles:putAttribute name="scripts">
		<script type="text/javascript">
			$(document).ready(function() {
				$('#menu-home').removeClass("active");
				$('#menu-ranking').addClass("active");
				
				
				var medal = '${medal.medal.type}';
				$('#medal-'+ medal).attr('selected', 'selected');
			});
			
			
			
		</script>
	</tiles:putAttribute>
	
</tiles:insertDefinition>
