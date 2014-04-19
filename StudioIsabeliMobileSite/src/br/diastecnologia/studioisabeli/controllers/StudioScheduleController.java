package br.diastecnologia.studioisabeli.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.diastecnologia.studioisabeli.Utils.Configurations;
import br.diastecnologia.studioisabeli.beans.CalendarEvent;
import br.diastecnologia.studioisabeli.beans.CalendarEventColor;
import br.diastecnologia.studioisabeli.beans.StudioSchedule;
import br.diastecnologia.studioisabeli.beans.Week;
import br.diastecnologia.studioisabeli.business.StudioScheduleBusiness;
import br.diastecnologia.studioisabeli.session.StudioSession;

@Resource
public class StudioScheduleController extends Controller {

	private StudioScheduleBusiness studioScheduleBusiness;
	
	public StudioScheduleController(HttpServletResponse _response, Result _result, StudioSession _session, ServletContext _context, StudioScheduleBusiness _studioScheduleBusiness) {
		super(_response, _result, _session, _context);
		this.studioScheduleBusiness = _studioScheduleBusiness;
	}
	
	@Path("/agenda-do-studio")
	public void studioSchedule(){
		
	}
	
	@Path("/agenda-do-studio-horarios")
	public void getStudioScheduleClasses( long start, long end){
		
		start += (end - start) /2;
		Date middle = new Date( start * 1000 );
		Week week = studioScheduleBusiness.getWeek(middle);
		List<StudioSchedule> sches = studioScheduleBusiness.getWeekStudioSchedule(middle);
		List<CalendarEvent> events = getEvents(sches, week);
		
		result.use( Results.json() ).withoutRoot().from( events ).recursive().serialize();
	}
	
	private List<CalendarEvent> getEvents( List<StudioSchedule> sches , Week week ){
		List<CalendarEvent> events = new ArrayList<CalendarEvent>();
		
		Calendar calendar = Calendar.getInstance();
		
		for( StudioSchedule sche : sches ){
			CalendarEvent event = new CalendarEvent();
			
			calendar.setTime( week.getBegin() );
			calendar.add( Calendar.DAY_OF_MONTH, sche.getWeekDay() );
			calendar.set( Calendar.HOUR_OF_DAY, sche.getBeginHour() );
			event.setStart( Configurations.dateFormat.format( calendar.getTime() ) );
			
			calendar.set( Calendar.HOUR_OF_DAY, sche.getEndHour() );
			event.setEnd( Configurations.dateFormat.format( calendar.getTime() ) );
			
			event.setTitle( "Há vagas" );
			event.setColor( CalendarEventColor.Blue );
			if( sche.getCustomersMaxCount() <= sche.getClasses().size()){
				event.setTitle( "Lotado" );
				event.setColor( CalendarEventColor.Black );
			}
			
			events.add(event);
		}
		
		return events;
	}

}
