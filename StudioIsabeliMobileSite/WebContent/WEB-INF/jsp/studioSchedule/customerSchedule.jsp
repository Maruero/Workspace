<!DOCTYPE html>
<html>
<head>
<link href='fullcalendar/fullcalendar.css' rel='stylesheet' />
<link href='fullcalendar/fullcalendar.print.css' rel='stylesheet' media='print' />
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
			selectable: false,
			selectHelper: true,
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
<div id='calendar'></div>
</body>
</html>
