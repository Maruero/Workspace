package br.diastecnologia.studioisabeli.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import br.com.caelum.vraptor.ioc.Component;
import br.diastecnologia.studioisabeli.beans.ManagerUser;
import br.diastecnologia.studioisabeli.enums.UserPerfil;

@Component
public class UserDAO extends JdbcDaoSupport{

	@Autowired
	public void setDBConnection( DataSource datasource ) {
		setDataSource( datasource );
	}
	
	public ManagerUser getUser( String username, String password ){
		final String SQL = "select Perfil from manageruser where Username = ? and Password = ?";
		List<ManagerUser> users = getJdbcTemplate().query( SQL , managerUserMapper, username, password );
		ManagerUser user = users.size() > 0 ? users.get( 0 ) : null;
		
		if( user != null ){
			user.setUsername( username );
			user.setPassword( password );
		}
		return user;
	}
	
	private static RowMapper<ManagerUser> managerUserMapper = new RowMapper<ManagerUser>() {
		public ManagerUser mapRow(ResultSet result, int arg1) throws SQLException {
			ManagerUser user = new ManagerUser();
			user.setPerfil( UserPerfil.values()[ result.getInt( "Perfil" )]);
			return user;
		}
	};
	
}
