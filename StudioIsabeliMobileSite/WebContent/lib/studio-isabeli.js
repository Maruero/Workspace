/** ===============================================================
 * Vari�veis de configura��o
 ==================================================================**/
var timeToSplash = 5; //tempo que ap�s leva o usu�rio de volta para a splash.

$(document).ready(function() {
	
	formatDates();
	registerListeners();
	addSplashCookie();
	
});



/** ===============================================================
 * Fun��o usada para gravar o cookie de splash do usu�rio
 ==================================================================**/
function addSplashCookie(){
	var ex = new Date();
	ex.setTime( ex.getTime() + (timeToSplash * 60 * 1000 ));
	var expires = "expires="+ex.toGMTString();
	document.cookie = "splash=1; " + expires;
}


/** ===============================================================
 * Fun��o usada para enviar o usu�rio de volta para a splash screen
 ==================================================================**/
function registerListeners(){
	
	//On Focus
	$(window).focus(function(){
		if( sessionStorage.dateUserIsOut ){
			
			var diff = new Date().getTime() - sessionStorage.dateUserIsOut;
			diff = diff / 1000 / 60; //pegando os minutos
			
			if( diff > timeToSplash ){
				window.location = "splash";
			}
		}
	});
	
	$(window).blur(function(){
		sessionStorage.dateUserIsOut = new Date().getTime();
	});
}


/** ===============================================================
 * Fun��o usada para alterar o formato das datas no DOM.
 ==================================================================**/
function formatDates(){
	$('.date').each(function(date){
		var date = $(this).html();
		if( date != null && date.split('-').length == 3){
			date = date.split('-')[2] + '/' + date.split('-')[1] + '/' + date.split('-')[0]; 
		}
		$(this).html(date);
	});
}