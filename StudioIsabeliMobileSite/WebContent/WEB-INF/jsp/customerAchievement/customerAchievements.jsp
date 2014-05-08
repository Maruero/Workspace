<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="studioisabeli.template">
	<tiles:putAttribute name="head">
		<style>
			.bag-green{
				background-color: green;
			}
		</style>
	</tiles:putAttribute>

	<tiles:putAttribute name="content">
	
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="painel-title">Suas marcas</h3>
			</div>
			<div class="panel-body">
				<c:if test="${empty achievements}">
					<div class="alert alert-danger">
						<h5>Você ainda não tem nenhuma marca!</h5>
					</div>
				</c:if>
			</div>
			<c:if test="${not empty achievements}">
				<table class="table table-hover table-bordered table-condensed">
					<thead>
						<tr>
							<th>Data</th>
							<c:forEach items="${types}" var="type">
								<th>${type.name}</th>
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${achievements}" var="customerAchievement">
							<tr class="data-tr">
								<td class="date">${customerAchievement.date}</td>
								
								<c:forEach items="${types}" var="type">
									<c:set var="find" value="false"/> 
									<c:forEach items="${customerAchievement.achievements}" var="achiev">
										<c:if test="${type.achievementTypeID == achiev.achievementType.achievementTypeID}">
											<td typeID="${type.achievementTypeID}" value="${achiev.value}">${achiev.value}</td>
											<c:set var="find" value="true"/>
										</c:if>
									</c:forEach>
									<c:if test="${find == false}">
										<td typeID="${type.achievementTypeID}"></td>
									</c:if>
								</c:forEach>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
		
	</tiles:putAttribute>
	
	<tiles:putAttribute name="scripts">
		<script type="text/javascript">
			$(document).ready(function() {
				$('#menu-home').removeClass("active");
				$('#menu-suas-metas').addClass("active");
				
				addBadge();
				
			});
			
			function addBadge(){
				var i = 0;
				$('.data-tr').each(function(tr){
					$(this).find('td').each(function(td){
						
						//pulando a TD de data.
						if( $(this).attr('typeID') != null && $(this).attr('value') != null){
							var typeID = $(this).attr('typeID');
							
							if( i < $('.data-tr').length-1 && $($($('.data-tr')[i+1]).find('td[TypeID='+typeID+']')[0]).attr('value') != null){
								$(this).html( $(this).attr('value') + ' <span class="badge">'+ ($(this).attr('value') - $($($('.data-tr')[i+1]).find('td[TypeID='+typeID+']')[0]).attr('value')) +'</span>' );
							}
							
							if( ($(this).attr('value') - $($($('.data-tr')[i+1]).find('td[TypeID='+typeID+']')[0]).attr('value')) < 0 ){
								$(this).find('span').addClass('bag-green');
							}
						}
					});
					i++;
				});
			}
			
		</script>
	</tiles:putAttribute>
	
</tiles:insertDefinition>