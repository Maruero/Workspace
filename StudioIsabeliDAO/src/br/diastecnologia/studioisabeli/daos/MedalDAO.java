package br.diastecnologia.studioisabeli.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import br.com.caelum.vraptor.ioc.Component;
import br.diastecnologia.studioisabeli.beans.CustomerMedal;
import br.diastecnologia.studioisabeli.beans.Medal;
import br.diastecnologia.studioisabeli.dtos.CustomerRanking;
import br.diastecnologia.studioisabeli.enums.MedalType;

@Component
public class MedalDAO extends JdbcDaoSupport {

	@Autowired
	public void setDBConnection( DataSource datasource ) {
		setDataSource( datasource );
	}
	
	public List<CustomerMedal> getMedals( Integer customerID ){
		final String SQL = "select cm.CustomerID, cm.MedalID, cm.Date, cm.Description, m.Type, m.Points from customermedal cm " +
							"join medal m on m.Type = cm.Type where cm.CustomerID = ? order by cm.Date desc";
		return getJdbcTemplate().query( SQL , customerMedalMapper, customerID );
	}
	
	public List<CustomerRanking> getRanking( ){
		final String SQL = "select c.Name, count(g.Type) Gold, count(s.Type) Silver, count(b.Type) Bronze, sum(ifnull(g.Points, 0)) + sum(ifnull(s.Points, 0)) + sum(ifnull(b.Points, 0)) Points from customer c "+
							"join customermedal cm on c.CustomerID = cm.CustomerID "+
							"left outer join medal g on g.Type = cm.Type and g.Type = 0 "+
							"left outer join medal s on s.Type = cm.Type and s.Type = 1 "+
							"left outer join medal b on b.Type = cm.Type and b.Type = 2 "+
							"group by c.Name, g.Type, s.Type "+
							"order by points desc";
		return getJdbcTemplate().query( SQL , customerRankingMapper );
		
	}
	
	private static RowMapper<CustomerRanking> customerRankingMapper = new RowMapper<CustomerRanking>() {
		public CustomerRanking mapRow(ResultSet result, int arg1) throws SQLException {
			CustomerRanking ran = new CustomerRanking();
			
			ran.setName( result.getString( "Name" ));
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
