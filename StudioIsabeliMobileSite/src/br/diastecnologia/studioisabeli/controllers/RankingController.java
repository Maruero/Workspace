package br.diastecnologia.studioisabeli.controllers;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.diastecnologia.studioisabeli.beans.CustomerMedal;
import br.diastecnologia.studioisabeli.daos.CustomerTokenDAO;
import br.diastecnologia.studioisabeli.daos.MedalDAO;
import br.diastecnologia.studioisabeli.dtos.CustomerRanking;
import br.diastecnologia.studioisabeli.enums.MedalType;
import br.diastecnologia.studioisabeli.session.StudioSession;

@Resource
public class RankingController extends Controller{

	private MedalDAO medalDao;
	private StudioSession session;
	private CustomerTokenDAO customerTokenDao;
	
	public RankingController(HttpServletResponse _response, Result _result, StudioSession _session, ServletContext _context, MedalDAO _medalDao, CustomerTokenDAO _customerTokenDao) {
		super(_response, _result, _context);
		this.medalDao = _medalDao;
		this.session = _session;
		this.customerTokenDao = _customerTokenDao;
	}
	
	@Path("/suas-medalhas")
	public void medals(){
		List<CustomerMedal> medals = medalDao.getMedals( session.getCustomer().getCustomerID() );
		
		result.include( "goldCount" , getCountOf(MedalType.GOLD, medals) );
		result.include( "silverCount" , getCountOf(MedalType.SILVER, medals) );
		result.include( "bronzeCount" , getCountOf(MedalType.BRONZE, medals) );
		
		result.include( "medals" , medals );
	}
	
	private Integer getCountOf( MedalType type , List<CustomerMedal> medals ){
		Integer count = 0;
		
		for( CustomerMedal medal : medals ){
			if( medal.getMedal().getType() == type ){
				count++;
			}
		}
		
		return count;
	}
	
	@Path("/ranking")
	public void ranking(){
		List<CustomerRanking> ranking = medalDao.getRanking();
		result.include( "ranking", ranking );
		result.include( "visible", session.getCustomer().isVisible() );
	}
	
	@Path("/mudar-minha-visibilidade")
	public void toggleVisibility(){
		session.getCustomer().setVisible( !session.getCustomer().isVisible() );
		customerTokenDao.updateCustomer(session.getCustomer());
		result.redirectTo( RankingController.class ).ranking();
	}

}
