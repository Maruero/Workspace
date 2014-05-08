package br.diastecnologia.studioisabeli.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import br.com.caelum.vraptor.ioc.Component;
import br.diastecnologia.studioisabeli.beans.Customer;
import br.diastecnologia.studioisabeli.beans.CustomerClass;
import br.diastecnologia.studioisabeli.enums.ClassStatus;
import br.diastecnologia.studioisabeli.enums.ClassType;

@Component
public class CustomerClassDAO extends JdbcDaoSupport {

	@Autowired
	public void setDBConnection( DataSource datasource ) {
		setDataSource( datasource );
	}
	
	public void updateCustomerClass( CustomerClass clazz ){
		final String SQL = "update customerclass set Status = ? where CustomerID = ? and StudioScheduleID = ? and WeekID = ?";
		getJdbcTemplate().update( SQL , ClassStatus.getIndex(clazz.getStatus()),  clazz.getCustomerID(), clazz.getStudioScheduleID(), clazz.getWeekID());
	}
	
	public void removeCustomerClass( CustomerClass clazz ){
		final String SQL = "delete from customer where CustomerID = ? and StudioScheduleID = ? and WeekID = ?";
		getJdbcTemplate().update( SQL , clazz.getCustomerID(), clazz.getStudioScheduleID(), clazz.getWeekID());
	}
	
	public List<CustomerClass> getClassesOfWeek( Integer weekID , Integer customerID ){
		final String SQL = "select c.CustomerID, c.Name, cc.StudioScheduleID, cc.WeekID, cc.Type, cc.Status, cc.AlterBeginMinutes, cc.AlterEndMinutes from customer c "+ 
							"left outer join customerclass cc on c.CustomerID = cc.CustomerID "+
							"where cc.WeekID = ? and cc.CustomerID = ?";
		return getJdbcTemplate().query( SQL , customerClassMapper , weekID , customerID );
	}
	
	public List<CustomerClass> getClassesOfWeek( Integer weekID ){
		final String SQL = "select c.CustomerID, c.Name, cc.StudioScheduleID, cc.WeekID, cc.Type, cc.Status, cc.AlterBeginMinutes, cc.AlterEndMinutes from customer c "+ 
							"left outer join customerclass cc on c.CustomerID = cc.CustomerID "+
							"where cc.WeekID = ? or cc.WeekID is null";
		
		
		
		return getJdbcTemplate().query( SQL , customerClassMapper , weekID );
	}
	
	public void addCustomerClasses( Integer weekID ){
		final String SQL = "insert into customerclass (CustomerID, StudioScheduleID, WeekID, AlterBeginMinutes, AlterEndMinutes) " + 
				"select c.CustomerID, cs.StudioScheduleID, ?, cs.AlterBeginMinutes, cs.AlterEndMinutes from customer c "+ 
				"join customerschedule cs on cs.CustomerID = c.CustomerID "+
				"left outer join customerclass cc on c.CustomerID = cc.CustomerID and cc.StudioScheduleID = cs.StudioScheduleID and cc.WeekID = ? "+
				"where c.DeletedDate is null and cc.status is null";
		getJdbcTemplate().update( SQL, weekID, weekID );
	}
	
	public void addCustomerClass( Integer customerID, Integer studioScheduleID, Integer weekID , Integer alterBeginMinutes, Integer alterEndMinutes){
		final String SQL = "insert into customerclass( CustomerID, StudioScheduleID, WeekID , Type, AlterBeginMinutes, AlterEndMinutes ) values (?,?,?, 1, ?,?)";
		getJdbcTemplate().update(SQL, customerID, studioScheduleID, weekID , alterBeginMinutes, alterEndMinutes);
	}
	
	public void removeCustomerClass( Integer customerID, Integer studioScheduleID, Integer weekID ){
		final String SQL = "delete from customerclass where CustomerID = ? and StudioScheduleID = ? and WeekID = ?";
		getJdbcTemplate().update(SQL, customerID, studioScheduleID, weekID );
	}
	
	public void removeScheduleCustomerClasses( Integer customerID, Integer studioScheduleID, Integer weekID ){
		final String SQL = "delete from customerclass where CustomerID = ? and StudioScheduleID = ? and WeekID >= ? and Status = 0";
		getJdbcTemplate().update(SQL, customerID, studioScheduleID, weekID );
	}
	
	private static RowMapper<CustomerClass> customerClassMapper = new RowMapper<CustomerClass>() {
		public CustomerClass mapRow(ResultSet result, int arg1) throws SQLException {
			CustomerClass customerClass = new CustomerClass();
			
			Customer customer = new Customer( result.getInt( "CustomerID" ) , result.getString( "Name" ));
			
			customerClass.setCustomer(customer);
			customerClass.setCustomerID( customer.getCustomerID() );
			customerClass.setStudioScheduleID( result.getInt( "StudioScheduleID" ));
			customerClass.setWeekID( result.getInt( "WeekID" ));
			
			int type = result.getInt( "Type" );
			customerClass.setType( ClassType.values()[type] );
			
			int status = result.getInt( "Status" );
			customerClass.setStatus( ClassStatus.values()[status]);
			
			customerClass.setAlterBeginMinutes( result.getInt( "AlterBeginMinutes" ));
			customerClass.setAlterEndMinutes( result.getInt( "AlterEndMinutes" ));
			
			return customerClass;
		}
	};
	
}
