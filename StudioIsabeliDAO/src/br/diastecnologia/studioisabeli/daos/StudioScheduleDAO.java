package br.diastecnologia.studioisabeli.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import br.com.caelum.vraptor.ioc.Component;
import br.diastecnologia.studioisabeli.beans.StudioSchedule;
import br.diastecnologia.studioisabeli.beans.Week;

@Component
public class StudioScheduleDAO extends JdbcDaoSupport{

	@Autowired
	public void setDBConnection( DataSource datasource ) {
		setDataSource( datasource );
	}
	
	public Date getNextClass( Integer customerID ){
		final String SQL = "select date_add(date_add(adddate(w.Begin, ss.WeekDay), INTERVAL ss.BeginHour HOUR), INTERVAL cc.AlterBeginMinutes MINUTE) as date from customerclass cc "+
							"join week w on cc.WeekID = w.WeekID "+
							"join studioschedule ss on ss.StudioScheduleID = cc.StudioScheduleID "+
							"where CustomerID = ? "+
							"and date_add(adddate(w.Begin, ss.WeekDay), INTERVAL ss.BeginHour HOUR) > now() "+
							"order by date_add(adddate(w.Begin, ss.WeekDay), INTERVAL ss.BeginHour HOUR) limit 1";
		List<Date> dates = getJdbcTemplate().query( SQL, new RowMapper<Date>(){
			public Date mapRow(ResultSet result, int arg1) throws SQLException {
				return result.getTimestamp( "date" );
			}
		}, customerID);
		
		return dates.size() > 0 ? dates.get( 0 ): null;
	}
	
	public List<StudioSchedule> getStudioSchedules(){
		final String SQL = "select StudioScheduleID, WeekDay, BeginHour, EndHour, CustomersMaxCount from studioschedule";
		return getJdbcTemplate().query( SQL, studioScheduleMapper );
	}
	
	public void addStudioSchedule( Integer weekDay, Integer beginHour, Integer endHour, Integer customerMaxCount ){
		final String SQL = "insert into studioschedule (WeekDay, BeginHour, EndHour, CustomersMaxCount) values (?,?,?,?)";
		getJdbcTemplate().update( SQL, weekDay, beginHour, endHour, customerMaxCount);
	}
	
	public StudioSchedule getStudioSchedule( Integer scheduleID ){
		final String SQL = "select StudioScheduleID, WeekDay, BeginHour, EndHour, CustomersMaxCount from studioschedule where StudioScheduleID = ?";
		List<StudioSchedule> sches = getJdbcTemplate().query( SQL, studioScheduleMapper, scheduleID );
		return sches.size() > 0 ? sches.get( 0 ) : null;
	}
	
	public void updateStudioSchedule( StudioSchedule sche ){
		final String SQL = "update studioschedule set CustomersMaxCount = ? where StudioScheduleID = ?";
		getJdbcTemplate().update( SQL, sche.getCustomersMaxCount(), sche.getStudioScheduleID() );
	}
	
	public Week getWeek( Date middleDate ){
		final String SQL = "select WeekID, Begin, End from week  where Begin < ? and End > ?";
		List<Week> weeks = getJdbcTemplate().query(SQL, weekScheduleMapper, middleDate, middleDate);
		return weeks.size() > 0 ? weeks.get( 0 ) : null;
	}
	
	public void addCustomerSchedule( Integer studioScheduleID, Integer customerID , Integer alterBeginMinutes, Integer alterEndMinutes){
		final String SQL = "insert into customerschedule (StudioScheduleID, CustomerID, AlterBeginMinutes, AlterEndMinutes) values (?,?, ?,?)";
		getJdbcTemplate().update(SQL, studioScheduleID, customerID, alterBeginMinutes, alterEndMinutes);
	}
	
	public void removeCustomerSchedule( Integer studioScheduleID, Integer customerID ){
		final String SQL = "delete from customerschedule where StudioScheduleID = ? and CustomerID = ?";
		getJdbcTemplate().update(SQL, studioScheduleID, customerID);
	}
	
	
	public void removeStudioSchedule( Integer studioScheduleID ){
		final String SQL = "delete from studioschedule where StudioScheduleID = ?";
		getJdbcTemplate().update( SQL , studioScheduleID );
	}
	 
	public void addWeek( Week week ){
		final String SQL = "insert into week (Begin, End) values (?,?)";
		getJdbcTemplate().update( SQL , week.getBegin(), week.getEnd() );
	}
	
	private static RowMapper<Week> weekScheduleMapper = new RowMapper<Week>() {

		public Week mapRow(ResultSet result, int arg1) throws SQLException {
			Week w = new Week();
			w.setWeekID( result.getInt( "WeekID" ));
			w.setBegin( result.getDate("Begin" ));
			w.setEnd( result.getDate( "End" ));
			return w;
		}
	};
	
	private static RowMapper<StudioSchedule> studioScheduleMapper = new RowMapper<StudioSchedule>() {
		public StudioSchedule mapRow(ResultSet result, int arg1) throws SQLException {
			
			StudioSchedule sche = new StudioSchedule();
			sche.setStudioScheduleID( result.getInt( "StudioScheduleID" ));
			sche.setWeekDay( result.getInt( "WeekDay" ));
			sche.setBeginHour( result.getInt( "BeginHour" ));
			sche.setEndHour( result.getInt( "EndHour" ));
			sche.setCustomersMaxCount( result.getInt( "CustomersMaxCount" ));
			return sche;
		}
	};
	
	
}
