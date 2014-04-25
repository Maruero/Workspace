package br.diastecnologia.studioisabeli.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.diastecnologia.studioisabeli.beans.CalendarEvent;
import br.diastecnologia.studioisabeli.beans.StudioSchedule;
import br.diastecnologia.studioisabeli.beans.Week;
import br.diastecnologia.studioisabeli.business.StudioScheduleBusiness;
import br.diastecnologia.studioisabeli.session.StudioSession;
import br.diastecnologia.studioisabeli.utils.EventUtils;

@Resource
public class StudioScheduleController extends Controller {

	private StudioScheduleBusiness studioScheduleBusiness;
	private StudioSession session;
	
	public StudioScheduleController(HttpServletResponse _response, Result _result, StudioSession _session, ServletContext _context, StudioScheduleBusiness _studioScheduleBusiness) {
		super(_response, _result, _context);
		this.studioScheduleBusiness = _studioScheduleBusiness;
		this.session = _session;
	}
	
	@Path("/agenda-do-studio")
	public void studioSchedule(){
		
	}
	
	@Path("/agenda-do-studio-horarios")
	public void getStudioScheduleClasses( long start, long end){
		
		start += (end - start) /2;
		Date middle = new Date( start * 1000 );
		Week week = studioScheduleBusiness.getWeek(middle);
		List<StudioSchedule> sches = studioScheduleBusiness.getWeekStudioSchedule(middle, null);
		List<CalendarEvent> events = EventUtils.getEvents(sches, week);
		
		result.use( Results.json() ).withoutRoot().from( events ).recursive().serialize();
	}
	
	@Path("/sua-agenda")
	public void customerSchedule(){
		
	}
	
	@Path("/sua-agenda-horarios")
	public void getCustomerClasses( long start, long end){
		start += (end - start) /2;
		Date middle = new Date( start * 1000 );
		Week week = studioScheduleBusiness.getWeek(middle);
		List<StudioSchedule> sches = studioScheduleBusiness.getWeekStudioSchedule(middle, session.getCustomer().getCustomerID());
		List<CalendarEvent> events = EventUtils.getEventsOfACustomer(sches, week);
		
		result.use( Results.json() ).withoutRoot().from( events ).recursive().serialize();
	}

}
