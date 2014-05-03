<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="studioisabeli.template">
	<tiles:putAttribute name="content">
    
    	<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					<c:if test="${tip.tipID == null || tip.tipID == 0 }">
						Novo&nbsp;
					</c:if>
					Dica
				</h3>
		  	</div>
		  	<div class="panel-body">
    
		    	<form class="bs-example bs-example-form" role="form" action="salvar-dica" method="post" id="salvar-dica-form" name="salvar-dica-form">
		    		<input type="hidden" id="tipID" name=tip.tipID value="${tip.tipID}"/>
					
					<c:if test="${tip.tipID != null && tip.tipID != 0 }">
						<div class="input-group">
							<span class="input-group-addon">Data</span>
							<input type="text" readonly="readonly" class="form-control" value="${tip.date}"/>
						</div>
					</c:if>
					
					<div class="input-group">
						<span class="input-group-addon">Título</span>
						<input type="text" class="form-control" required autofocus placeholder="Digite o título da dica" name="tip.title" value="${tip.title}"/>
					</div>
					<div class="input-group">
						<span class="input-group-addon">Texto</span>
						<textarea class="form-control" required placeholder="Digite o texto da dica" name="tip.text" form="salvar-dica-form">${tip.text}</textarea>
					</div>
					
					<br/>
					<button class="btn btn-primary" type="submit">Salvar</button>
					
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
				$('#menu-dicas').addClass("active");
			});
		</script>
	</tiles:putAttribute>
	
</tiles:insertDefinition>
