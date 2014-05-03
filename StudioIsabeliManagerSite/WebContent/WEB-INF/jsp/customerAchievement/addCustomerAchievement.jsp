<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="studioisabeli.template">
	<tiles:putAttribute name="content">
	
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="painel-title">Nova Marca </h3><button type="button" class="btn btn-primary" onclick="$('#achievement-form').submit();">Salvar</button>
			</div>
			<div class="panel-body">
				<c:if test="${managerSession.message != null}">
					<div class="alert alert-success">
						<h5>${managerSession.message}</h5>
					</div>
				</c:if>
			
				<form id="achievement-form" action="salvar-marca-do-aluno" method="post" onsubmit="return validate();">
					<input type="hidden" name="customerAchievement.customer.customerID" value="${customer.customerID}"/>
				
				</form>
				
				<br/>
				<button type="button" class="btn btn-primary" onclick="addAchievement();">Adicionar Marca</button>
			</div>
			
		</div>
		
		<div class="input-group" id="achievement-template" style="display:none;">
		
			<input type="hidden" name="customerAchievement.achievements[].achievementType.achievementTypeID" id="input-achiev-type-id" value="0"/>
			<input type="hidden" name="customerAchievement.achievements[].customer.customerID" value="${customer.customerID}"/>
		
			<div class="input-group-btn">
				<button type="button" id="type-button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"><span id="type-button-label" typeID="0">Selecione</span> <span style="min-width:10px;" class="caret"></span></button>
				<ul class="dropdown dropdown-menu">
					
					<c:forEach items="${types}" var="type">
						<li><a href="#" onclick="changeButtonValue(this);" typeID="${type.achievementTypeID}" >${type.name}</a></li>
					</c:forEach>
				</ul>
			</div>
			<input type="text" class="form-control" name="customerAchievement.achievements[].value"/>
			<div class="input-group-btn">
				<button type="button" id="type-button" onclick="removeAchievement(this);" class="btn btn-default dropdown-toggle" data-toggle="dropdown">Remover</button>
			</div>
		</div>
		
	</tiles:putAttribute>
	
	<tiles:putAttribute name="scripts">
		<script type="text/javascript">
		
			function validate(){
				
				var validated = true;
				$('#input-achiev-type-id').each(function(typeID){
					if( $(this).val() == '0'){
						alert( "'Selecione' não é uma meta válida." );
						validated = false;
					}
				});
				
				return validated;
			}
		
			function changeButtonValue( button ){
				var toInclude = {
					'typeId' : $(button).closest('div').find('#type-button-label').attr('typeID'),
					'label' : $(button).closest('div').find('#type-button-label').html()
				};
				var toRemove = {
					'typeId' : $(button).attr('typeID'),
					'label' : $(button).html()
				};
				
				$(button).closest('div').find('#type-button-label').html( toRemove.label );
				$(button).closest('div').find('#type-button-label').attr('typeID', toRemove.typeId);
				$(button).closest('div').parent().find('#input-achiev-type-id').val( toRemove.typeId );
				
				$('.dropdown-menu').each(function(menu){
					if( toInclude.typeId != 0 ){
						$(this).append(
							'<li><a href="#" onclick="changeButtonValue(this);" typeID="'+ toInclude.typeId+'" >'+toInclude.label+'</a></li>'
						);
					}
					$(this).find( '[typeID='+toRemove.typeId+']').parent().remove();
				});
				
			}
			
			function removeAchievement( button ){
				
				var toInclude = {
					'typeId' : $(button).closest('div').parent().find('#type-button-label').attr('typeID'),
					'label' : $(button).closest('div').parent().find('#type-button-label').html()
				};
				
				$('.dropdown-menu').each(function(menu){
					if( toInclude.typeId != 0 ){
						$(this).append(
							'<li><a href="#" onclick="changeButtonValue(this);" typeID="'+ toInclude.typeId+'" >'+toInclude.label+'</a></li>'
						);
					}
				});
				$(button).closest('.input-group').remove();
			}
			
			function addAchievement(){
				var achie = $('#achievement-template').clone();
				$(achie).removeAttr('id');
				$(achie).removeAttr('style');
				
				$('#achievement-form').append(
					achie
				);
				
				$('.dropdown-toggle').dropdown();
			}
		
			$(document).ready(function() {
				$('#menu-home').removeClass("active");
				$('#menu-alunos').addClass("active");
				
				addAchievement();
				$('.dropdown-toggle').dropdown();
			});
		</script>
	</tiles:putAttribute>
	
</tiles:insertDefinition>