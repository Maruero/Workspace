<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="studioisabeli.cleanTemplate">
	
	<tiles:putAttribute name="head">
		<style>
			.float-right{
				float: right;
				margin-left: 5px;
			}
		</style>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="content">

	
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h4>Dica <span class="date float-right">${tip.date}</span></h4>
			</div>
			<div class="panel-body">
				<h3>${tip.title}</h3>
				${tip.text}
			</div>
		</div>
		
		<c:if test="${tip.previousTipID != null && tip.previousTipID != 0}">
			<button type="button" class="btn btn-primary btn" onclick="previous();">
			  <span class="glyphicon glyphicon-step-backward"></span> Dica anterior
			</button>
		</c:if>
		
		<button type="button" class="btn btn-primary btn float-right" onclick="scraps();">
			Avançar
		</button>
		
		<c:if test="${tip.nextTipID != null && tip.nextTipID != 0}">
			<button type="button" class="btn btn-primary btn float-right" onclick="next();">
				  <span class="glyphicon glyphicon-step-forward"></span> Próxima dica
			</button>
		</c:if>
		
	</tiles:putAttribute>
	
	<tiles:putAttribute name="scripts">
		<script>
			function next(){
				window.location = 'tip?tipID=${tip.nextTipID}';
			}
			
			function previous(){
				window.location = 'tip?tipID=${tip.previousTipID}';
			}
			
			function scraps(){
				window.location = 'scrap';
			}
		</script>
	
	</tiles:putAttribute>
	
</tiles:insertDefinition>