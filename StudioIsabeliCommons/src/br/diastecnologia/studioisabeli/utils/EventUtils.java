package br.diastecnologia.studioisabeli.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.diastecnologia.studioisabeli.beans.CalendarEvent;
import br.diastecnologia.studioisabeli.beans.CalendarEventColor;
import br.diastecnologia.studioisabeli.beans.CustomerClass;
import br.diastecnologia.studioisabeli.beans.StudioSchedule;
import br.diastecnologia.studioisabeli.beans.Week;

public class EventUtils {
	
	public static List<CalendarEvent> getEventsForManagerSite( List<StudioSchedule> sches , Week week ){
		List<CalendarEvent> events = new ArrayList<CalendarEvent>();
		
		for( StudioSchedule sche : sches ){
			CalendarEvent event = getEvent(sche, week);
			String customers = "(";
			
			if( sche.getClasses().size() > 0 ){
				for( CustomerClass clazz : sche.getClasses()){
					customers = customers.replace( ")" , ", " );
					customers += clazz.getCustomer().getName() + ")";
				}
			}
			else{
				customers += ")";
			}
			
			
			event.setTitle( event.getTitle() + " " + customers );
			event.setCustomersMaxCount(sche.getCustomersMaxCount());
			event.setStudioScheduleID( sche.getStudioScheduleID() );
			
			events.add(event);
		}
		
		return events;
	}
	
	private static CalendarEvent getEvent( StudioSchedule sche , Week week ){
		CalendarEvent event = new CalendarEvent();
		Calendar calendar = Calendar.getInstance();
		
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
		return event;
	}
	
	public static List<CalendarEvent> getEvents( List<StudioSchedule> sches , Week week ){
		List<CalendarEvent> events = new ArrayList<CalendarEvent>();
		
		for( StudioSchedule sche : sches ){
			events.add(getEvent(sche, week));
		}
		
		return events;
	}
	
	public static List<CalendarEvent> getEventsOfACustomer( List<StudioSchedule> sches , Week week ){
		List<CalendarEvent> events = new ArrayList<CalendarEvent>();
		
		Calendar calendar = Calendar.getInstance();
		
		for( StudioSchedule sche : sches ){
			if( sche.getClasses() != null && sche.getClasses().size() > 0 ){
				CalendarEvent event = new CalendarEvent();
				
				calendar.setTime( week.getBegin() );
				calendar.add( Calendar.DAY_OF_MONTH, sche.getWeekDay() );
				calendar.set( Calendar.HOUR_OF_DAY, sche.getBeginHour() );
				event.setStart( Configurations.dateFormat.format( calendar.getTime() ) );
				
				calendar.set( Calendar.HOUR_OF_DAY, sche.getEndHour() );
				event.setEnd( Configurations.dateFormat.format( calendar.getTime() ) );
				
				CustomerClass clazz = sche.getClasses().get( 0 );
				switch( clazz.getStatus() ){
					case DONE:
						event.setTitle("Realizada");
						event.setColor(CalendarEventColor.Green);
						break;
					case SCHEDULED:
						event.setTitle("Agendada");
						event.setColor(CalendarEventColor.Blue);
						break;	
					case NOT_DONE:
						event.setTitle("Não realizada");
						event.setColor(CalendarEventColor.Red);
						break;							
				}
				events.add(event);
			}
			
			
		}
		
		return events;
	}
}

