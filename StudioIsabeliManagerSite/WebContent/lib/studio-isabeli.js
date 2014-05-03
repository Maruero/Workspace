$(document).ready(function() {
	
	$('.date').each(function(date){
		var date = $(this).html();
		if( date != null && date.split('-').length == 3){
			date = date.split('-')[2] + '/' + date.split('-')[1] + '/' + date.split('-')[0]; 
		}
		$(this).html(date);
	});
	
	
});