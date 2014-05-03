package br.diastecnologia.studioisabeli.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import br.com.caelum.vraptor.ioc.Component;
import br.diastecnologia.studioisabeli.beans.Achievement;
import br.diastecnologia.studioisabeli.beans.AchievementType;
import br.diastecnologia.studioisabeli.beans.Customer;
import br.diastecnologia.studioisabeli.beans.CustomerAchievement;

@Component
public class AchievementDAO extends JdbcDaoSupport {

	@Autowired
	public void setDBConnection( DataSource datasource ) {
		setDataSource( datasource );
	}
	
	public List<AchievementType> getAchievementTypes(){
		final String SQL = "select AchievementTypeID, Name from achievementtype order by Name";
		return getJdbcTemplate().query( SQL, achievementTypeMapper );
	}
	
	public List<AchievementType> getCustomerAchievementTypes( Integer customerID ){
		final String SQL = "select distinct act.AchievementTypeID, act.Name from achievementtype as act "+
							"join achievement a on a.AchievementTypeID = act.AchievementTypeID "+
							"where a.CustomerID = ? order by act.Name";
		return getJdbcTemplate().query( SQL , achievementTypeMapper , customerID);
	}
	
	public List<Achievement> getAchievements( Integer customerID, Integer achievementNumber ){
		final String SQL = "select AchievementTypeID, CustomerID, Value, AchievementNumber from achievement where CustomerID = ? and AchievementNumber = ?";
		return getJdbcTemplate().query( SQL , achievementMapper, customerID, achievementNumber );
	}
	
	public List<CustomerAchievement> getCustomerAchievements( Integer customerID , boolean withAchievements){
		final String SQL = "select CustomerID, AchievementNumber, Date from customerachievement where CustomerID = ? order by Date desc;";
		List<CustomerAchievement> customerAchievements = getJdbcTemplate().query(SQL, customerAchievement, customerID );
		if( withAchievements){
			for( CustomerAchievement achie : customerAchievements ){
				achie.setAchievements( getAchievements(customerID, achie.getAchievementNumber() ) );
			}
		}
		return customerAchievements;
	}
	
	public void addAchievement( Achievement achie ){
		final String SQL = "insert into achievement (AchievementTypeID, CustomerID, Value, AchievementNumber) values (?,?,?,?)";
		getJdbcTemplate().update( SQL , achie.getAchievementType().getAchievementTypeID(), achie.getCustomer().getCustomerID(), achie.getValue(), achie.getAchievementNumber() );
	}
	
	public synchronized void addCustomerAchievement( CustomerAchievement achie , boolean addAchievements ){
		achie.setAchievementNumber( getNextAchievementNumber(achie.getCustomer().getCustomerID() ) );
		
		final String SQL = "insert into customerachievement (CustomerID, AchievementNumber, Date) values (?,?,now())";
		getJdbcTemplate().update( SQL, achie.getCustomer().getCustomerID(), achie.getAchievementNumber() );
		
		if( addAchievements && achie.getAchievements() != null ){
			for( Achievement achievement : achie.getAchievements() ){
				achievement.setAchievementNumber( achie.getAchievementNumber() );
				addAchievement(achievement);
			}
		}
	}
	
	public int getNextAchievementNumber( Integer customerID ){
		final String SQL = "select ifnull(max(AchievementNumber),0) from customerachievement where CustomerID = ?";
		return getJdbcTemplate().queryForInt(SQL, customerID) +1;
	}
	
	private RowMapper<AchievementType> achievementTypeMapper = new RowMapper<AchievementType>() {
		public AchievementType mapRow(ResultSet result, int arg1) throws SQLException {
			AchievementType type = new AchievementType();
			type.setAchievementTypeID( result.getInt( "AchievementTypeID" ));
			type.setName( result.getString( "Name" ));
			return type;
		}
	};
	
	private RowMapper<Achievement> achievementMapper = new RowMapper<Achievement>() {
		public Achievement mapRow(ResultSet result, int arg1) throws SQLException {
			Achievement achievement = new Achievement();
			achievement.setAchievementType( new AchievementType( result.getInt( "AchievementTypeID" )));
			achievement.setCustomer( new Customer( result.getInt( "CustomerID" ), null ));
			achievement.setAchievementNumber( result.getInt( "AchievementNumber" ));
			achievement.setValue( result.getDouble( "Value" ));
			return achievement;
		}
	};
	
	private RowMapper<CustomerAchievement> customerAchievement = new RowMapper<CustomerAchievement>() {
		public CustomerAchievement mapRow(ResultSet result, int arg1) throws SQLException {
			CustomerAchievement achie = new CustomerAchievement();
			achie.setDate( result.getDate( "Date" ));
			achie.setCustomer( new Customer( result.getInt( "CustomerID" ), null ) );
			achie.setAchievementNumber( result.getInt( "AchievementNumber" ));
			return achie;
		}
	};
	
}
