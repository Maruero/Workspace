<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="studioisabeli.template">
	
	<tiles:putAttribute name="head">
		<link href='fullcalendar/fullcalendar.css' rel='stylesheet' />
		<link href='css/custom-theme/jquery-ui-1.10.3.custom.css' rel='stylesheet' />
		<style>
			body {
				margin-top: 40px;
				text-align: center;
				font-size: 14px;
				font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
				}
		
			#calendar {
				margin: 10px auto;
			}
			
			.ui-dialog .ui-dialog-title{
				color: #fff;
			}
			
			.no-close .ui-dialog-titlebar-close {
				display: none 
			}
		</style>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="content">
		<div id="dialog-loading" title="Por favor, espere." style="display:none;">
			<div id="dialog-content"></div>
			<div>
				Carregando agenda!
			</div>
		</div>
		<div id='calendar'></div>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="scripts">
		<script src='lib/jquery-ui.custom.min.js'></script>
		<script src='fullcalendar/fullcalendar.min.js'></script>
		
		<script>
			$(document).ready(function() {
				
				$('#menu-sua-agenda').addClass("active");
				
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
					height: 1400,
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
					select: function(start, end, allDay) {
						var title = prompt('Event Title:');
						if (title) {
							calendar.fullCalendar('renderEvent',
								{
									title: title,
									start: start,
									end: end,
									allDay: allDay
								},
								true // make the event "stick"
							);
						}
						calendar.fullCalendar('unselect');
					},
					editable: false,
					events: 
					{
						url: 'sua-agenda-horarios',
			       		type: 'GET',
			        	data: {
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
					resizable: false,
					dialogClass: 'no-close'
				});
			}
		</script>
	</tiles:putAttribute>
	
</tiles:insertDefinition>
