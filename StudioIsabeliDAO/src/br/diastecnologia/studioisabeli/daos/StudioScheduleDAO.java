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
	
	public List<StudioSchedule> getStudioSchedules(){
		final String SQL = "select StudioScheduleID, WeekDay, BeginHour, EndHour, CustomersMaxCount from studioschedule";
		return getJdbcTemplate().query( SQL, studioScheduleMapper );
	}
	
	public Week getWeek( Date middleDate ){
		final String SQL = "select WeekID, Begin, End from week  where Begin < ? and End > ?";
		List<Week> weeks = getJdbcTemplate().query(SQL, weekScheduleMapper, middleDate, middleDate);
		return weeks.size() > 0 ? weeks.get( 0 ) : null;
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
