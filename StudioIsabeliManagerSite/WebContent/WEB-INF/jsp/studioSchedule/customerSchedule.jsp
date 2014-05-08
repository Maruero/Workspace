<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="studioisabeli.template">

	<tiles:putAttribute name="head">
	
		<link href='fullcalendar/fullcalendar.css' rel='stylesheet' />
		<link href='fullcalendar/fullcalendar.print.css' rel='stylesheet' media='print' />
		<link href='css/custom-theme/jquery-ui-1.10.3.custom.css' rel='stylesheet' />
		<style>
			body {
				margin-top: 40px;
				text-align: center;
				font-size: 14px;
				font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
				}
		
			#calendar {
				margin: 0 auto;
			}
	</style>
	</tiles:putAttribute>

	<tiles:putAttribute name="content">
		<h4>${customer.name}</h4>
		<div id="dialog-loading" title="Por favor, espere." style="display:none;">
			<div id="dialog-content-loading"></div>
			<div>
				Carregando agenda!
			</div>
		</div>
		
		<div id="dialog" title="Horário de Agendamento" style="display:none;">
			<div id="dialog-content"></div>
			<div>
			</div>
		</div>
		
		<div id="calendar"></div>
		
	</tiles:putAttribute>
	
	<tiles:putAttribute name="scripts">
		<script src='lib/jquery-ui.custom.min.js'></script>
		<script src='fullcalendar/fullcalendar.min.js'></script>
	
		<script type="text/javascript">
		$(document).ready(function() {
		
			$('#menu-home').removeClass("active");
			$('#menu-alunos').addClass("active");
			
			var date = new Date();
			var d = date.getDate();
			var m = date.getMonth();
			var y = date.getFullYear();
			
			var calendar = $('#calendar').fullCalendar({
				header: {
					left: 'prev,next',
					center: 'title',
					right: ''
				},
				monthNamesShort: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
				dayNamesShort: ['Dom','Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb'],
				columnFormat: {week: 'ddd dd/MM'},
				titleFormat: {week: "dd {à dd 'de' MMM 'de' yyyy}"},
				defaultView: 'agendaWeek',
				weekends: false,
				axisFormat: 'HH:mm',
				timeFormat: 'HH:mm{ - HH:mm}',
				minTime: 5,
				maxTime: 21,
				slotMinutes: 15,
				snapMinutes: 60,
				aspectRatio: 0.3,
				allDaySlot: false,
				selectable: false,
				selectHelper: true,
				loading: function( isLoading, view ){
					if( isLoading ){
						if(!$('#dialog-loading').is(':data(dialog)')){
							buildLoadingDialog();
						}
						$('#dialog-loading').dialog("open");
					}else{
						$('#dialog-loading').dialog('close');
					}
				},
				eventClick: function(event, element) {
					openEditDialog(event);
				},
				editable: false,
				events: 
				{
					url: 'agenda-do-studio-horarios',
		       		type: 'GET',
		        	data: {
		        		customerID : ${customer.customerID}
		        	},
		        	error: function() {
		            	alert('Agenda temporariamente indisponível, por favor, tente mais tarde.');
		        	},
		        	allDay: false
		        	
		    	}
			});
			
		});
	
		function buildLoadingDialog(){
			$('#dialog-loading').dialog({
				autoOpen: false,
				modal: true,
				resizable: false
			});
		}
		
		$(document).ready(function(){
			$('#dialog').dialog({
				autoOpen: false,
				modal: true,
				resizable: false,
				buttons: {
					'Adicionar reposição': function(){
						if($('#dialog-content').attr('Lotado') == 'true'){
							var yes = confirm( "O horário está lotado, mesmo assim deseja adicionar?");
							if( !yes ){
								return;
							}
						}
						
						if($('#dialog-content').attr('containsRepo') == 'true'){
							alert("Você só pode adicionar o aluno em horários que ele não esteja!");
							return false;
						}
						
						var resp = confirm('Confirma a inclusão do aluno neste horário?');
						if( resp ){
							addCustomerClass( );
						}
					},
					'Adicionar semanalmente': function(){
						if($('#dialog-content').attr('Lotado') == 'true'){
							var yes = confirm( "O horário está lotado, mesmo assim deseja adicionar?");
							if( !yes ){
								return;
							}
						}
						
						
						if($('#dialog-content').attr('contains') == 'true'){
							alert("Você só pode adicionar o aluno em horários que ele não esteja!");
							return false;
						}
						
						var resp = confirm('Confirma a inclusão do aluno neste horário?');
						if( resp ){
							addCustomerSchedule( );
						}
					},
					'Remover reposição': function(){
						
						if($('#dialog-content').attr('containsRepo') == 'false'){
							alert("Você só pode remover o aluno de horários dele!");
							return false;
						}
						var name = '${customer.name}';
						var customerID = ${customer.customerID};
						var ok = confirm("Tem certeza que deseja remover " +name+ " deste horário?");
						if( ok ){
							$.ajax({
								url: "remover-reposicao-do-aluno",
								data: {
									'studioScheduleID' : $("#dialog-content").attr("event-studioScheduleID"),
									'customerID' : customerID,
									'weekID' : $("#dialog-content").attr("event-weekID")
								},
								success: function(data){
									alert("Aluno removido com sucesso.");
									$('#dialog').dialog('close');
									$('#calendar').fullCalendar( 'refetchEvents' );
								},
								error: function(){
									alert("Ocorreu um erro, por favor, tente novamente mais tarde.");
									$('#dialog').dialog('close');
								}
							});
						}
					},
					
					'Remover semanalmente': function(){
						
						if($('#dialog-content').attr('contains') == 'false'){
							alert("Você só pode remover o aluno de horários dele!");
							return false;
						}
						var name = '${customer.name}';
						var customerID = ${customer.customerID};
						var ok = confirm("Tem certeza que deseja remover " +name+ " deste horário?");
						if( ok ){
							$.ajax({
								url: "remover-horario-do-aluno",
								data: {
									'studioScheduleID' : $("#dialog-content").attr("event-studioScheduleID"),
									'customerID' : customerID,
									'weekID' : $("#dialog-content").attr("event-weekID")
								},
								success: function(data){
									alert("Aluno removido com sucesso.");
									$('#dialog').dialog('close');
									$('#calendar').fullCalendar( 'refetchEvents' );
								},
								error: function(){
									alert("Ocorreu um erro, por favor, tente novamente mais tarde.");
									$('#dialog').dialog('close');
								}
							});
						}
					}
				},
				width: 400,
				height: 250
			});
		});
		
		function openEditDialog(event){
			
			var customer = '${customer.name}';
			
			$('#dialog-content').html('');
			$('#dialog-content').append(event.title);
			$('#dialog-content').attr("event-studioScheduleID", event.studioScheduleID);
			$('#dialog-content').attr("event-weekID", event.weekID);
			
			if( event.title.indexOf(customer+'*R*') >= 0 ){
				$('#dialog-content').attr('containsRepo', 'true');
			}else{
				$('#dialog-content').attr('containsRepo', 'false');
			}
			
			if( event.title.indexOf(customer) >= 0 ){
				$('#dialog-content').attr('contains', 'true');
			}else{
				$('#dialog-content').attr('contains', 'false');
			}
			
			if( event.title.indexOf("Lotado") >= 0){
				$('#dialog-content').attr('Lotado', 'true');
			}else{
				$('#dialog-content').attr('Lotado', 'false');
			}
			
			$("#dialog").dialog("open");
			
		}
		
		function addCustomerSchedule( ){
			
			var customerID = ${customer.customerID};
			var studioScheduleID = $('#dialog-content').attr("event-studioScheduleID");
			
			var alterBeginMinutes = prompt("Horário de início:", "00");
			var alterEndMinutes = prompt("Horário de término:", "60");
			
			$.ajax({
				url: 'adicionar-horario-do-aluno',
				data: {
					'studioScheduleID' : studioScheduleID,
					'customerID' : customerID,
					'alterBeginMinutes' : alterBeginMinutes,
					'alterEndMinutes' : alterEndMinutes
				}, 
				type: 'post',
				success: function(data){
					$('#calendar').fullCalendar( 'refetchEvents' );
					$('#dialog').dialog('close');
				},
				error: function(){
					alert("Ocorreu um erro.\nPor favor, tente mais tarde.")
				}
			});
		}
		
		function addCustomerClass( ){
			
			var customerID = ${customer.customerID};
			var studioScheduleID = $('#dialog-content').attr("event-studioScheduleID");
			var weekID = $('#dialog-content').attr("event-weekID");
			
			var alterBeginMinutes = prompt("Entra no minuto:", "00");
			var alterEndMinutes = prompt("Sai no minuto:", "60");
			
			$.ajax({
				url: 'adicionar-reposicao-do-aluno',
				data: {
					'studioScheduleID' : studioScheduleID,
					'customerID' : customerID,
					'weekID' : weekID,
					'alterBeginMinutes' : alterBeginMinutes,
					'alterEndMinutes' : alterEndMinutes
				}, 
				type: 'post',
				success: function(data){
					$('#calendar').fullCalendar( 'refetchEvents' );
					$('#dialog').dialog('close');
				},
				error: function(){
					alert("Ocorreu um erro.\nPor favor, tente mais tarde.")
				}
			});
		}
	</script>
</tiles:putAttribute>

</tiles:insertDefinition>