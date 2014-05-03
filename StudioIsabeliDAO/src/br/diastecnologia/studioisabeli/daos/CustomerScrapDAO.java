package br.diastecnologia.studioisabeli.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import br.com.caelum.vraptor.ioc.Component;
import br.diastecnologia.studioisabeli.beans.CustomerScrap;

@Component
public class CustomerScrapDAO extends JdbcDaoSupport{

	@Autowired
	public void setDBConnection( DataSource datasource ) {
		setDataSource( datasource );
	}
	
	public List<CustomerScrap> getCustomerScraps(){
		final String SQL = "select CustomerID, ScrapNumber, Date, Text from customerscrap order by Date desc";
		return getJdbcTemplate().query( SQL , customerScrapMapper );
	}
	
	public void addCustomerScrap( Integer customerID, String text ){
		final String SQL = "insert into customerscrap ( CustomerID, ScrapNumber, Text, Date) values (?,?,?, now())";
		final Integer nextNumber = getNextNumber(customerID);
		getJdbcTemplate().update(SQL, customerID, nextNumber+1 , text );
	}
	
	public void updateCustomerScrap( CustomerScrap scrap ){
		final String SQL = "update customerscrap set Text = ? where CustomerID = ? and ScrapNumber = ?";
		getJdbcTemplate().update( SQL , scrap.getText(), scrap.getCustomerID(), scrap.getScrapNumber() );
	}
	
	public void removeCustomerScrap( CustomerScrap scrap ){
		final String SQL = "delete from customerscrap where CustomerID = ? and ScrapNumber = ?";
		getJdbcTemplate().update( SQL , scrap.getCustomerID(), scrap.getScrapNumber() );
	}
	
	private Integer getNextNumber( Integer customerID ){
		final String SQL = "select max(ScrapNumber) from customerscrap where CustomerID = ?";
		return getJdbcTemplate().queryForInt( SQL , customerID );
	}
	
	public List<CustomerScrap> getLastsCustomerScrap(Integer customerID ){
		final String SQL = "select S.CustomerID, S.ScrapNumber, S.Date, S.Text from customerscrap S " +
							"where S.CustomerID = ? order by S.ScrapNumber desc limit 5";
		
		return getJdbcTemplate().query(SQL, customerScrapMapper, customerID);
	}
	
	public CustomerScrap getLastCustomerScrap(Integer customerID ){
		final String SQL = "select CS.CustomerID, CS.ScrapNumber, CS.Text, CS.Date, "+
							"(select ScrapNumber from customerscrap where CustomerID = CS.CustomerID and ScrapNumber > CS.ScrapNumber order by Date limit 1) as NextScrapNumber, "+
							"(select ScrapNumber from customerscrap where CustomerID = CS.CustomerID and ScrapNumber < CS.ScrapNumber order by Date desc limit 1) as PreviousScrapNumber "+
							"from customerscrap CS "+
							"where CS.CustomerID = ? "+ 
							"and CS.DeletedDate is null "+
							"order by Date desc limit 1";
		
		List<CustomerScrap> scraps = getJdbcTemplate().query(SQL, customerScrapMapperWithPrevAndNext, customerID);
		return scraps.size() > 0 ? scraps.get( 0 ) : null;
	}
	
	public CustomerScrap getCustomerScrap(Integer customerID , Integer scrapNumber){
		final String SQL = "select CS.CustomerID, CS.ScrapNumber, CS.Text, CS.Date, "+
							"(select ScrapNumber from customerscrap where CustomerID = CS.CustomerID and ScrapNumber > CS.ScrapNumber order by Date limit 1) as NextScrapNumber, "+
							"(select ScrapNumber from customerscrap where CustomerID = CS.CustomerID and ScrapNumber < CS.ScrapNumber order by Date desc limit 1) as PreviousScrapNumber "+
							"from customerscrap CS "+
							"where CS.CustomerID = ? "+ 
							"and CS.ScrapNumber = ? "+
							"and CS.DeletedDate is null "+
							"order by Date desc limit 1";
		
		List<CustomerScrap> scraps = getJdbcTemplate().query(SQL, customerScrapMapperWithPrevAndNext, customerID, scrapNumber);
		return scraps.size() > 0 ? scraps.get(0) : null;
	}
	
	private static RowMapper<CustomerScrap> customerScrapMapper = new RowMapper<CustomerScrap>() {
		public CustomerScrap mapRow(ResultSet result, int arg1) throws SQLException {
			CustomerScrap scrap = new CustomerScrap(result.getInt( "CustomerID" ), result.getInt( "ScrapNumber" ), result.getString( "Text" ), result.getDate( "Date" ));
			return scrap;
		}
	};
	
	private static RowMapper<CustomerScrap> customerScrapMapperWithPrevAndNext = new RowMapper<CustomerScrap>() {
		public CustomerScrap mapRow(ResultSet result, int arg1) throws SQLException {
			CustomerScrap scrap = new CustomerScrap(result.getInt( "CustomerID" ), result.getInt( "ScrapNumber" ), result.getString( "Text" ), result.getDate( "Date" ));
			scrap.setNextScrapNumber( result.getInt( "NextScrapNumber" ));
			scrap.setPreviousScrapNumber( result.getInt( "PreviousScrapNumber" ));
			return scrap;
		}
	};
	
}
