<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="studioisabeli.template">
	<tiles:putAttribute name="content">
    
    	<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					<c:if test="${customer.customerID == null || customer.customerID == 0 }">
						Novo&nbsp;
					</c:if>
					Aluno
				</h3>
		  	</div>
		  	<div class="panel-body">
    
		    	<form class="bs-example bs-example-form" role="form" action="salvar-aluno" method="post" id="salvar-aluno-form">
		    		<input type="hidden" id="customerID" name="customer.customerID" value="${customer.customerID}"/>
					<div class="input-group">
						<span class="input-group-addon">Nome</span>
						<input type="text" class="form-control" required autofocus placeholder="Digite o nome" name="customer.name" value="${customer.name}"/>
						<span class="input-group-btn">
							<button class="btn btn-default" type="submit">Salvar</button>
						</span>
					</div>
					
					<c:if test="${customer.customerID != null && customer.customerID != 0 }">
						<div class="input-group">
							<span class="input-group-addon">Token</span>
							<input type="text" id="token" readonly="readonly" class="form-control" placeholder="Nenhum token gerado" value="${token}"/>
							<span class="input-group-btn">
								<c:choose>
									<c:when test="${token == null}">
										<button id="button-gerar-token" class="btn btn-default" type="button" onClick="return gerarToken();">Gerar</button>
									</c:when>
									<c:otherwise>
										<button id="button-gerar-token" disabled="disabled" class="btn btn-default" type="button" onClick="return gerarToken();">Gerar</button>
									</c:otherwise>
								</c:choose>
							</span>
						</div>
					</c:if>
				</form>
			</div>
		</div>

	</tiles:putAttribute>
	
	<tiles:putAttribute name="scripts">
		<script type="text/javascript">
			function gerarToken(){
				
				$.ajax({
					url : 'gerar-token-de-aluno',
					data : 'customerID=' + $('#customerID').val(),
					success: function(data){
						$('#token').val(data.string);
						$('#button-gerar-token').attr('disabled', 'disabled');
					},
					error: function(){
						alert('Ocorreu um problema, por favor tente novamente mais tarde.');
					}
				});
				
				return false;
			}
			
			$(document).ready(function() {
				$('#menu-home').removeClass("active");
				$('#menu-alunos').addClass("active");
			});
		</script>
	</tiles:putAttribute>
	
</tiles:insertDefinition>
