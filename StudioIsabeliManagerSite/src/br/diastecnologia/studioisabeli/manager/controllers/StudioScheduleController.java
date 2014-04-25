package br.diastecnologia.studioisabeli.manager.controllers;

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
import br.diastecnologia.studioisabeli.controllers.Controller;
import br.diastecnologia.studioisabeli.daos.StudioScheduleDAO;
import br.diastecnologia.studioisabeli.utils.EventUtils;

@Resource
public class StudioScheduleController extends Controller {

	private StudioScheduleBusiness studioScheduleBusiness;
	private StudioScheduleDAO studioScheduleDao;
	
	public StudioScheduleController(HttpServletResponse _response, Result _result, ServletContext _context, StudioScheduleBusiness _studioScheduleBusiness, StudioScheduleDAO _studioScheduleDao ) {
		super(_response, _result, _context);
		this.studioScheduleBusiness = _studioScheduleBusiness;
		this.studioScheduleDao = _studioScheduleDao;
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
		List<CalendarEvent> events = EventUtils.getEventsForManagerSite(sches, week);
		
		result.use( Results.json() ).withoutRoot().from( events ).recursive().serialize();
	}
	
	@Path("/adicionar-horario")
	public void addStudioScheduleClass( Integer weekDay, Integer beginHour, Integer endHour, Integer customersMaxCount ){
		studioScheduleDao.addStudioSchedule(weekDay, beginHour, endHour, customersMaxCount);
		result.use( Results.json() ).from( "Adicionado com sucesso!" ).serialize();
	}
	
	@Path("/atualizar-horario")
	public void updateStudioScheduleClass( StudioSchedule sche ){
		studioScheduleDao.updateStudioSchedule(sche);
		result.use( Results.json() ).from( "Atualizado com sucesso!" ).serialize();
	}
	
	@Path("/remover-horario")
	public void removeStudioScheduleClass( Integer studioScheduleID ){
		studioScheduleDao.removeStudioSchedule(studioScheduleID);
		result.use( Results.json() ).from( "Removido com sucesso!" ).serialize();
	}
}
