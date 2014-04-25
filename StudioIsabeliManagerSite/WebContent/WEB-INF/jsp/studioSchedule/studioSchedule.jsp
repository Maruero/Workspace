<!DOCTYPE html>
<html>
<head>
<link href='fullcalendar/fullcalendar.css' rel='stylesheet' />
<link href='fullcalendar/fullcalendar.print.css' rel='stylesheet' media='print' />
<link href='css/smoothness/jquery.ui.theme.css' rel='stylesheet' />
<link href='css/smoothness/jquery-ui.css' rel='stylesheet' />
<link href='css/smoothness/jquery-ui.min.css' rel='stylesheet' />

<script src='lib/jquery.min.js'></script>
<script src='lib/jquery-ui.custom.min.js'></script>
<script src='fullcalendar/fullcalendar.min.js'></script>
<script>

	$(document).ready(function() {
	
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
			allDaySlot: false,
			selectable: true,
			selectHelper: true,
			select: function(start, end, allDay) {
				var customersMaxCount = prompt('Número máximo de alunos:');
				var shouldCall = true;
				if( customersMaxCount == null || customersMaxCount == 0){
					calendar.fullCalendar('unselect');
					return;
				}
				if( start.getDay() != end.getDay()){
					shouldCall = false;
				}
				
				if( !shouldCall ){
					alert('Agendamento inválido.');
					calendar.fullCalendar('unselect');
					return;
				}
				
				addStudioSchedule( start.getDay(), start.getHours(), end.getHours(), customersMaxCount);
				
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
	        	},
	        	error: function() {
	            	alert('Agenda temporariamente indisponível, por favor, tente mais tarde.');
	        	},
	        	allDay: false
	        	
	    	}
		});
		
	});

	$(document).ready(function(){
		$('#dialog').dialog({
			autoOpen: false,
			modal: true,
			resizable: false,
			buttons: {
				Salvar : function(){
					var ok = confirm("Tem certeza que deseja salvar a alterção no número máximo de alunos?");
					if( ok ){
						$.ajax({
							url: "atualizar-horario",
							data: {
								'sche.studioScheduleID' : $("#dialog-content").attr("event-studioScheduleID"),
								'sche.customersMaxCount' : $('#dialog-content-customersMaxCount').val()
							},
							success: function(data){
								alert("Informações salvas com sucesso.");
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
				Remover: function(){
					var ok = confirm("Tem certeza que deseja remover?");
					if( ok ){
						$.ajax({
							url: "remover-horario",
							data: {
								'studioScheduleID' : $("#dialog-content").attr("event-studioScheduleID")
							},
							success: function(data){
								alert("Horário removido com sucesso.");
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
				Fechar: function(){
					$(this).dialog('close');
				}
			},
			width: 500,
			height: 250
		});
	});
	
	function openEditDialog(event){
		
		$('#dialog-content').html('');
		$('#dialog-content').append(event.title);
		$('#dialog-content').attr("event-studioScheduleID", event.studioScheduleID);
		$('#dialog-content-customersMaxCount').val(event.customersMaxCount);
		$("#dialog").dialog("open");
		
	}
	
	function addStudioSchedule( weekDay, beginHour, endHour, customersMaxCount ){
		$.ajax({
			url: 'adicionar-horario',
			data: 'weekDay=' + weekDay + '&beginHour=' + beginHour + '&endHour=' + endHour + '&customersMaxCount=' + customersMaxCount,
			type: 'post',
			success: function(data){
				$('#calendar').fullCalendar( 'refetchEvents' );
			},
			error: function(){
				alert("Ocorreu um erro.\nPor favor, tente mais tarde.")
			}
			
			
		});
	}
	
</script>
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
</head>
<body>
<div id="calendar"></div>

<div id="dialog" title="Horário de Agendamento" style="display:none;">
	<div id="dialog-content"></div>
	<div>
		Máximo de alunos: <input type="text" id="dialog-content-customersMaxCount" value=""/>
	</div>
</div>

</body>
</html>
