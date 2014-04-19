package br.diastecnologia.studioisabeli.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.diastecnologia.studioisabeli.beans.CustomerClass;
import br.diastecnologia.studioisabeli.beans.StudioSchedule;
import br.diastecnologia.studioisabeli.beans.Week;
import br.diastecnologia.studioisabeli.daos.CustomerClassDAO;
import br.diastecnologia.studioisabeli.daos.StudioScheduleDAO;

@Component
public class StudioScheduleBusiness {

	private StudioScheduleDAO studioScheduleDAO;
	private CustomerClassDAO customerClassDAO;
	
	public StudioScheduleBusiness(StudioScheduleDAO _studioScheduleDAO, CustomerClassDAO _customerClassDAO ){
		this.studioScheduleDAO = _studioScheduleDAO;
		this.customerClassDAO = _customerClassDAO;
	}
	
	public List<StudioSchedule> getStudioSchedule(){
		return studioScheduleDAO.getStudioSchedules();
	}
	
	public List<StudioSchedule> getWeekStudioSchedule( Date middle ){
		List<StudioSchedule> sches = studioScheduleDAO.getStudioSchedules();
		Week week = getWeek(middle);
		customerClassDAO.addCustomerClasses(week.getWeekID());
		List<CustomerClass> weekClasses = customerClassDAO.getClassesOfWeek(week.getWeekID());
		return bind(sches, weekClasses);
	}
	
	private List<StudioSchedule> bind( List<StudioSchedule> sches, List<CustomerClass> weekClasses ){
		
		for( StudioSchedule sche : sches ){
			sche.setClasses( new ArrayList<CustomerClass>());
			for( CustomerClass ccclass : weekClasses ){
				if( ccclass.getStudioScheduleID() == sche.getStudioScheduleID() ){
					sche.getClasses().add(ccclass);
				}
			}
		}
		return sches;
	}
	
	public Week getWeek( Date middle ){
		Week week = studioScheduleDAO.getWeek(middle);
		if( week == null ){
			week = createWeek(middle);
			studioScheduleDAO.addWeek(week);
			week = studioScheduleDAO.getWeek(middle);
		}
		return week;
	}
	
	private Week createWeek( Date middle ){
		Week week = new Week();
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(middle);
		
		calendar.add( Calendar.DAY_OF_MONTH, -3 );
		calendar.set( Calendar.HOUR_OF_DAY, 0 );
		calendar.set( Calendar.MINUTE, 0 );
		calendar.set( Calendar.SECOND, 0 );
		
		week.setBegin( calendar.getTime());
		
		calendar.add( Calendar.DAY_OF_MONTH, 6 );
		week.setEnd( calendar.getTime() );
		
		return week;
	}
	
}
