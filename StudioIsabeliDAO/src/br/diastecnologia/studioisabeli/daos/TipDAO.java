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
		final String SQL = "select TipID, Title, Text, Date from tip where DeletedDate is null order by Date desc";
		return getJdbcTemplate().query( SQL, tipMapper );
	}
	
	public void addTip( String title, String text ){
		final String SQL = "insert into tip (Title, Text, Date) values (?,?,now())";
		getJdbcTemplate().update( SQL , title, text );
	}
	
	public void updateTip( Tip tip ){
		final String SQL = "update tip set Title = ?, Text = ? where TipID = ?";
		getJdbcTemplate().update( SQL , tip.getTitle(), tip.getText(), tip.getTipID() );
	}

	public void removeTip( Integer tipID ){
		final String SQL = "update tip set DeletedDate = now() where TipID = ?";
		getJdbcTemplate().update( SQL , tipID );
	}
	
	public Tip getLastTip(){
		final String SQL = "select T.TipID, T.Title, T.Text, T.Date, "+ 
							"(select TipID from tip where TipID < T.TipID order by Date desc limit 1) as PrevID, "+
							"(select TipID from tip where TipID > T.TipID order by Date limit 1) as NextID "+
							"from tip T "+
							"order by T.TipID desc limit 1";
		List<Tip> tips = getJdbcTemplate().query(SQL, tipMapperWithPrevAndNext);
		return tips.size() > 0 ? tips.get( 0 ) : null;
	}
	
	public Tip getTip( Integer tipID ){
		final String SQL = "select T.TipID, T.Title, T.Text, T.Date, "+ 
							"(select TipID from tip where TipID < T.TipID order by Date desc limit 1) as PrevID, "+
							"(select TipID from tip where TipID > T.TipID order by Date limit 1) as NextID "+
							"from tip T "+
							"where T.TipID = ? "+
							"order by T.TipID desc limit 1";
		List<Tip> tips = getJdbcTemplate().query(SQL, tipMapperWithPrevAndNext, tipID);
		return tips.size() > 0 ? tips.get( 0 ) : null;
	}
	
	private static RowMapper<Tip> tipMapper = new RowMapper<Tip>() {
		public Tip mapRow(ResultSet result, int arg1) throws SQLException {
			
			Tip tip = new Tip(result.getInt("TipID"), result.getString( "Text" ), result.getDate( "Date" ));
			tip.setTitle( result.getString( "Title" ) );
		
			return tip;
		}
	};
	
	private static RowMapper<Tip> tipMapperWithPrevAndNext = new RowMapper<Tip>() {
		public Tip mapRow(ResultSet result, int arg1) throws SQLException {
			
			Tip tip = new Tip(result.getInt("TipID"), result.getString( "Text" ), result.getDate( "Date" ));
			tip.setTitle( result.getString( "Title" ) );
			tip.setPreviousTipID( result.getInt("PrevID"));
			tip.setNextTipID( result.getInt("NextID"));
		
			return tip;
		}
	};
	
}
