package br.diastecnologia.studioisabeli.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import br.com.caelum.vraptor.ioc.Component;
import br.diastecnologia.studioisabeli.beans.Tip;

@Component
public class TipDAO extends JdbcDaoSupport {

	@Autowired
	public void setDBConnection( DataSource datasource ) {
		setDataSource( datasource );
	}
	
	public List<Tip> getTips(){
		final String SQL = "select TipID, Text, Date from tip order by Date desc";
		return getJdbcTemplate().query( SQL, tipMapper );
	}
	
	public void addTip( String text ){
		final String SQL = "insert into tip (Text, Date) values (?,now())";
		getJdbcTemplate().update( SQL , text );
	}
	
	public void updateTip( Tip tip ){
		final String SQL = "update tip set Text = ? where TipID = ?";
		getJdbcTemplate().update( SQL , tip.getText(), tip.getTipID() );
	}

	public void removeTip( Tip tip ){
		final String SQL = "delete from tip where TipID = ?";
		getJdbcTemplate().update( SQL , tip.getTipID() );
	}
	
	public Tip getLastTip(){
		final String SQL = "select T.TipID, T.Text, T.Date, N.TipID NextTipID, P.TipID PreviousTipID from tip T " +
							"left outer join tip N on T.TipID +1 = N.TipID " +
							"left outer join tip P on T.TipID -1 = P.TipID " +
							"order by T.TipID desc limit 1";
		List<Tip> tips = getJdbcTemplate().query(SQL, tipMapper);
		return tips.size() > 0 ? tips.get( 0 ) : null;
	}
	
	public Tip getTip( Integer tipID ){
		final String SQL = "select T.TipID, T.Text, T.Date, N.TipID NextTipID, P.TipID PreviousTipID from tip T " +
							"left join tip N on T.TipID +1 = N.TipID " +
							"left join tip P on T.TipID -1 = P.TipID " +
							"where T.TipID = ?";
		List<Tip> tips = getJdbcTemplate().query(SQL, tipMapper, tipID);
		return tips.size() > 0 ? tips.get( 0 ) : null;
	}
	
	private static RowMapper<Tip> tipMapper = new RowMapper<Tip>() {
		public Tip mapRow(ResultSet result, int arg1) throws SQLException {
			
			Tip tip = new Tip(result.getInt("TipID"), result.getString( "Text" ), result.getDate( "Date" ));
			tip.setNextTipID( result.getInt( "NextTipID" ));
			tip.setPreviousTipID( result.getInt( "PreviousTipID"));
		
			return tip;
		}
	};
	
}
