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
		final String SQL = "insert into customerscrap ( CustomerID, ScrapNumber, Text, Date) values (?,?,?, now)";
		getJdbcTemplate().update(SQL, customerID, getNextNumber(customerID), text );
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
	
	public CustomerScrap getLastCustomerScrap(Integer customerID ){
		final String SQL = "select S.CustomerID, S.ScrapNumber, S.Date, S.Text, N.ScrapNumber NextScrapNumber, P.ScrapNumber PreviousScrapNumber from customerscrap S " +
							"left outer join customerscrap N on S.CustomerID = N.CustomerID and S.ScrapNumber +1 = N.ScrapNumber " +
							"left outer join customerscrap P on S.CustomerID = P.CustomerID and S.ScrapNumber -1 = P.ScrapNumber " +
							"where S.CustomerID = ? order by S.ScrapNumber desc limit 1";
		
		List<CustomerScrap> scraps = getJdbcTemplate().query(SQL, customerScrapMapper, customerID);
		return scraps.size() > 0 ? scraps.get(0) : null;
	}
	
	public CustomerScrap getCustomerScrap(Integer customerID , Integer scrapNumber){
		final String SQL = "select S.CustomerID, S.ScrapNumber, S.Date, S.Text, N.ScrapNumber NextScrapNumber, P.ScrapNumber PreviousScrapNumber from customerscrap S " +
							"left outer join customerscrap N on S.CustomerID = N.CustomerID and S.ScrapNumber +1 = N.ScrapNumber " +
							"left outer join customerscrap P on S.CustomerID = P.CustomerID and S.ScrapNumber -1 = P.ScrapNumber " +
							"where S.CustomerID = ? and S.ScrapNumber = ?";
		
		List<CustomerScrap> scraps = getJdbcTemplate().query(SQL, customerScrapMapper, customerID, scrapNumber);
		return scraps.size() > 0 ? scraps.get(0) : null;
	}
	
	private static RowMapper<CustomerScrap> customerScrapMapper = new RowMapper<CustomerScrap>() {
		public CustomerScrap mapRow(ResultSet result, int arg1) throws SQLException {
			
			CustomerScrap scrap = new CustomerScrap(result.getInt( "CustomerID" ), result.getInt( "ScrapNumber" ), result.getString( "Text" ), result.getDate( "Date" ));
			scrap.setNextScrapNumber( result.getInt( "NextScrapNumber" ));
			scrap.setPreviousScrapNumber( result.getInt( "PreviousScrapNumber" ));
			
			return scrap;
		}
	};
	
}
