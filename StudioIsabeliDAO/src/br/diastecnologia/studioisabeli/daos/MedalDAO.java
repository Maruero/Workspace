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
import br.diastecnologia.studioisabeli.beans.CustomerMedal;
import br.diastecnologia.studioisabeli.beans.Medal;
import br.diastecnologia.studioisabeli.dtos.CustomerRanking;
import br.diastecnologia.studioisabeli.enums.MedalType;

@Component
public class MedalDAO extends JdbcDaoSupport {

	public static void main(String[] args){
		System.out.println( MedalType.GOLD.name() );
	}
	
	@Autowired
	public void setDBConnection( DataSource datasource ) {
		setDataSource( datasource );
	}
	
	public void addMedal( Integer customerID, String description, MedalType type ){
		final String SQL = "insert into customermedal (CustomerID, Description, Type, Date )values(?,?,?,now())";
		getJdbcTemplate().update( SQL , customerID, description, MedalType.getIndex(type));
	}
	
	public void updateMedal( CustomerMedal medal ){
		final String SQL = "update customermedal set Description = ?, Type = ? where MedalID = ?";
		getJdbcTemplate().update( SQL , medal.getDescription(), MedalType.getIndex(medal.getMedal().getType()), medal.getMedalID() );
	}
	
	public void removeMedal( CustomerMedal medal ){
		final String SQL = "delete from customermedal where MedalID = ?";
		getJdbcTemplate().update( SQL , medal.getMedalID() );
	}
	
	public List<CustomerMedal> getMedals( Integer customerID ){
		final String SQL = "select cm.CustomerID, cm.MedalID, cm.Date, cm.Description, m.Type, m.Points from customermedal cm " +
							"join medal m on m.Type = cm.Type where cm.CustomerID = ? order by cm.Date desc";
		return getJdbcTemplate().query( SQL , customerMedalMapper, customerID );
	}
	
	public CustomerMedal getMedal( Integer customerID , Integer medalID ){
		final String SQL = "select cm.CustomerID, cm.MedalID, cm.Date, cm.Description, m.Type, m.Points from customermedal cm " +
							"join medal m on m.Type = cm.Type where cm.CustomerID = ? and cm.medalID = ? order by cm.Date desc";
		List<CustomerMedal> medals = getJdbcTemplate().query( SQL , customerMedalMapper, customerID , medalID );
		return medals.size() > 0 ? medals.get( 0 ) : null;
	}
	
	public List<CustomerRanking> getRanking( ){
		final String SQL = "select c.CustomerID, c.Name, count(g.Type) Gold, count(s.Type) Silver, count(b.Type) Bronze, sum(ifnull(g.Points, 0)) + sum(ifnull(s.Points, 0)) + sum(ifnull(b.Points, 0)) Points from customer c "+
							"left outer join customermedal cm on c.CustomerID = cm.CustomerID "+
							"left outer join medal g on g.Type = cm.Type and g.Type = 0 "+
							"left outer join medal s on s.Type = cm.Type and s.Type = 1 "+
							"left outer join medal b on b.Type = cm.Type and b.Type = 2 " +
							"where c.DeletedDate is null and c.Visible = 1 "+
							"group by c.Name "+
							"order by points desc";
		return getJdbcTemplate().query( SQL , customerRankingMapper );
		
	}
	
	private static RowMapper<CustomerRanking> customerRankingMapper = new RowMapper<CustomerRanking>() {
		public CustomerRanking mapRow(ResultSet result, int arg1) throws SQLException {
			CustomerRanking ran = new CustomerRanking();
			ran.setCustomer( new Customer(result.getInt( "CustomerID" ), result.getString( "Name" )));
			ran.setGoldCount( result.getInt( "Gold" ));
			ran.setSilverCount( result.getInt( "Silver" ));
			ran.setBronzeCount( result.getInt( "Bronze" ));
			ran.setPoints( result.getInt( "Points" ));
			
			return ran;
		}
	};
	
	private static RowMapper<CustomerMedal> customerMedalMapper = new RowMapper<CustomerMedal>() {
		public CustomerMedal mapRow(ResultSet result, int arg1) throws SQLException {
			CustomerMedal customerMedal = new CustomerMedal();
			
			customerMedal.setCustomerID( result.getInt( "CustomerID" ));
			customerMedal.setMedalID( result.getInt( "MedalID" ));
			customerMedal.setDate( result.getDate( "Date" ));
			customerMedal.setDescription( result.getString( "Description" ));
			
			Medal medal = new Medal();
			medal.setType( MedalType.values()[ result.getInt( "Type" )]);
			medal.setPoints( result.getInt( "Points" ));
			customerMedal.setMedal(medal);
			
			return customerMedal;
		}
	};
	
}
