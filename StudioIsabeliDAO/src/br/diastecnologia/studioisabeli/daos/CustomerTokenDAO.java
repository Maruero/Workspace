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
import br.diastecnologia.studioisabeli.beans.CustomerToken;

@Component
public class CustomerTokenDAO extends JdbcDaoSupport{

	@Autowired
	public void setDBConnection( DataSource datasource ) {
		setDataSource( datasource );
	}
	
	public CustomerToken getCustomerToken( String token ){
		final String SQL = "select CustomerID, Token from customertoken where Token = ?";
		List<CustomerToken> tokens = getJdbcTemplate().query(SQL, customerTokenMapper, token);
		return tokens.size() > 0 ? tokens.get( 0 ) : null;
	}
	
	public List<CustomerToken> getCustomerTokens( Integer customerID ){
		final String SQL = "select CustomerID, Token from customertoken where CustomerID = ?";
		return getJdbcTemplate().query(SQL, customerTokenMapper, customerID);
	}
	
	public CustomerToken getCustomerToken( Integer customerID ){
		final String SQL = "select CustomerID, Token from customertoken where CustomerID = ?";
		List<CustomerToken> tokens = getJdbcTemplate().query(SQL, customerTokenMapper, customerID);
		return tokens.size() > 0 ? tokens.get( 0 ) : null;
	}
	
	public List<CustomerToken> getCustomerTokens( ){
		final String SQL = "select CustomerID, Token from customertoken";
		return getJdbcTemplate().query(SQL, customerTokenMapper);
	}
	
	public CustomerToken addCustomerToken( Integer customerID, String token ){
		final String SQL = "insert into customertoken (CustomerID, Token) values (?,?)";
		getJdbcTemplate().update(SQL, customerID, token);
		return new CustomerToken(token, customerID);
	}
	
	public Customer getCustomer( String token ){
		final String SQL = "select C.CustomerID, C.Name from customer C " +
							"join customertoken T on T.CustomerID = C.CustomerID " +
							"where T.Token = ? and C.DeletedDate is null";
		List<Customer> customers = getJdbcTemplate().query( SQL , customerMapper, token );
		return customers.size() > 0 ? customers.get( 0 ) : null;
	}
	
	public List<Customer> getCustomers(){
		final String SQL = "select CustomerID, Name from customer where DeletedDate is null order by Name";
		return getJdbcTemplate().query( SQL,  customerMapper );
	}
	
	public Customer getCustomer( Integer customerID ){
		final String SQL = "select CustomerID, Name from customer " +
							"where CustomerID = ? and DeletedDate is null";
		List<Customer> customers = getJdbcTemplate().query( SQL , customerMapper, customerID );
		return customers.size() > 0 ? customers.get( 0 ) : null;
	}
	
	public void addCustomer( String name ){
		final String SQL = "insert into customer (Name) values (?)";
		getJdbcTemplate().update( SQL, name );
	}
	
	public void removeCustomer( Integer customerID ){
		final String SQL = "update customer set DeletedDate = now() where customerID = ?";
		getJdbcTemplate().update(SQL, customerID);
	}
	
	public void updateCustomer( Customer customer ){
		final String SQL = "update customer set Name = ? where CustomerID = ?";
		getJdbcTemplate().update( SQL , customer.getName() , customer.getCustomerID() );
	}
	
	public CustomerToken addCustomerToken( CustomerToken customerToken ){
		return addCustomerToken( customerToken.getCustomerID(), customerToken.getToken());
	}
	
	private static RowMapper<CustomerToken> customerTokenMapper = new RowMapper<CustomerToken>(){
		public CustomerToken mapRow(ResultSet result, int arg1) throws SQLException {
			return new CustomerToken(result.getString("Token"), result.getInt("CustomerID"));
		}
	};
	
	private static RowMapper<Customer> customerMapper = new RowMapper<Customer>() {
		public Customer mapRow(ResultSet result, int arg1) throws SQLException {
			return new Customer(result.getInt( "CustomerID" ) , result.getString( "Name" ));
		}
	};
	
}
