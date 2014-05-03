package br.diastecnologia.studioisabeli.manager.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.diastecnologia.studioisabeli.beans.CalendarEvent;
import br.diastecnologia.studioisabeli.beans.CalendarEventColor;
import br.diastecnologia.studioisabeli.beans.CustomerClass;
import br.diastecnologia.studioisabeli.beans.StudioSchedule;
import br.diastecnologia.studioisabeli.beans.Week;
import br.diastecnologia.studioisabeli.business.StudioScheduleBusiness;
import br.diastecnologia.studioisabeli.controllers.Controller;
import br.diastecnologia.studioisabeli.daos.CustomerClassDAO;
import br.diastecnologia.studioisabeli.daos.CustomerTokenDAO;
import br.diastecnologia.studioisabeli.daos.StudioScheduleDAO;
import br.diastecnologia.studioisabeli.dtos.CustomerPresence;
import br.diastecnologia.studioisabeli.enums.ClassStatus;
import br.diastecnologia.studioisabeli.utils.EventUtils;

@Resource
public class StudioScheduleController extends Controller {

	private StudioScheduleBusiness studioScheduleBusiness;
	private StudioScheduleDAO studioScheduleDao;
	private CustomerTokenDAO customerTokenDao;
	private CustomerClassDAO customerClassDao;
	
	public StudioScheduleController(HttpServletResponse _response, Result _result, ServletContext _context, StudioScheduleBusiness _studioScheduleBusiness, StudioScheduleDAO _studioScheduleDao , CustomerTokenDAO _customerTokenDao, CustomerClassDAO _customerClassDao) {
		super(_response, _result, _context);
		this.studioScheduleBusiness = _studioScheduleBusiness;
		this.studioScheduleDao = _studioScheduleDao;
		this.customerTokenDao = _customerTokenDao;
		this.customerClassDao = _customerClassDao;
	}
	
	@Path("/agenda-do-aluno")
	public void customerSchedule( Integer customerID ){
		result.include("customer" , customerTokenDao.getCustomer(customerID) );
	}
	
	
	@Path("/agenda-do-studio")
	public void studioSchedule(){
		
	}
	
	@Path("/presenca-alunos")
	public void customerPresence( String presences ){
		List<CustomerPresence> customerPresences =  new Gson().fromJson(presences, new TypeToken<List<CustomerPresence>>() { private static final long serialVersionUID = 1L; }.getType());
		for( CustomerPresence pre : customerPresences ){
			CustomerClass clazz = new CustomerClass();
			clazz.setCustomer(pre.getCustomer());
			clazz.setCustomerID( pre.getCustomer().getCustomerID() );
			clazz.setWeekID( pre.getWeekID() );
			clazz.setStudioScheduleID( pre.getStudioScheduleID() );
			clazz.setStatus( pre.getPresence() ? ClassStatus.DONE : ClassStatus.NOT_DONE );
			customerClassDao.updateCustomerClass(clazz);
		}
		result.use( Results.json() ).from( "Gravado com sucesso!" ).serialize();
	}
	
	
	@Path("/agenda-do-studio-horarios")
	public void getStudioScheduleClasses( long start, long end, Integer customerID ){
		
		start += (end - start) /2;
		Date middle = new Date( start * 1000 );
		Week week = studioScheduleBusiness.getWeek(middle);
		List<StudioSchedule> sches = studioScheduleBusiness.getWeekStudioSchedule(middle, null);
		List<CalendarEvent> events = EventUtils.getEventsForManagerSite(sches, week);
		
		if( customerID != null && customerID != 0 ){
			EventUtils.setColor(events, customerID);
		}
		
		result.use( Results.json() ).withoutRoot().from( events ).recursive().serialize();
	}
	
	@Path("/adicionar-horario")
	public void addStudioScheduleClass( Integer weekDay, Integer beginHour, Integer endHour, Integer customersMaxCount ){
		studioScheduleDao.addStudioSchedule(weekDay, beginHour, endHour, customersMaxCount);
		result.use( Results.json() ).from( "Adicionado com sucesso!" ).serialize();
	}
	
	@Path("/adicionar-horario-do-aluno")
	public void addCustomerSchedule( Integer studioScheduleID, Integer customerID ){
		studioScheduleDao.addCustomerSchedule(studioScheduleID, customerID);
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
	
	@Path("/remover-horario-do-aluno")
	public void removeCustomerSchedule( Integer studioScheduleID , Integer customerID, Integer weekID ){
		customerClassDao.removeScheduleCustomerClasses(customerID, studioScheduleID, weekID);
		studioScheduleDao.removeCustomerSchedule(studioScheduleID, customerID);
		result.use( Results.json() ).from( "Removido com sucesso!" ).serialize();
	}
	
	@Path("/remover-reposicao-do-aluno")
	public void removeCustomerClass( Integer studioScheduleID , Integer customerID, Integer weekID ){
		customerClassDao.removeCustomerClass(customerID, studioScheduleID, weekID);
		result.use( Results.json() ).from( "Removido com sucesso!" ).serialize();
	}
	
	@Path("/adicionar-reposicao-do-aluno")
	public void addCustomerClass( Integer studioScheduleID , Integer customerID, Integer weekID ){
		customerClassDao.addCustomerClass(customerID, studioScheduleID, weekID);
		result.use( Results.json() ).from( "Removido com sucesso!" ).serialize();
	}
}
