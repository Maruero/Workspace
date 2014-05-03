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
				<h4>Recado <span class="date float-right">${scrap.date}</span></h4>
			</div>
			<div class="panel-body">
				${scrap.text}
			</div>
		</div>
		
		<c:if test="${scrap.previousScrapNumber != null && scrap.previousScrapNumber != 0}">
			<button type="button" class="btn btn-primary btn" onclick="previous();">
			  <span class="glyphicon glyphicon-step-backward"></span> Recado anterior
			</button>
		</c:if>
		
		<button type="button" class="btn btn-primary btn float-right" onclick="scraps();">
			Avançar
		</button>
		
		<c:if test="${scrap.nextScrapNumber != null && scrap.nextScrapNumber != 0}">
			<button type="button" class="btn btn-primary btn float-right" onclick="next();">
				  <span class="glyphicon glyphicon-step-forward"></span> Próximo recado
			</button>
		</c:if>
		
	</tiles:putAttribute>
	
	<tiles:putAttribute name="scripts">
		<script>
			function next(){
				window.location = 'scrap?scrapNumber=${scrap.nextScrapNumber}';
			}
			
			function previous(){
				window.location = 'scrap?scrapNumber=${scrap.previousScrapNumber}';
			}
			
			function scraps(){
				window.location = 'menu';
			}
		</script>
	
	</tiles:putAttribute>
	
</tiles:insertDefinition>