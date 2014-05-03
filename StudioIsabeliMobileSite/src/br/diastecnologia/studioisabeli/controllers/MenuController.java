package br.diastecnologia.studioisabeli.controllers;

import java.text.SimpleDateFormat;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.diastecnologia.studioisabeli.daos.StudioScheduleDAO;
import br.diastecnologia.studioisabeli.session.StudioSession;

@Resource
public class MenuController extends Controller{
	
	private StudioScheduleDAO studioScheduleDAO;
	private StudioSession session;
	
	public MenuController(HttpServletResponse _response, StudioSession _session, Result _result, ServletContext _context, StudioScheduleDAO _studioScheduleDao){
		super(_response, _result, _context);
		this.studioScheduleDAO = _studioScheduleDao;
		this.session = _session;
	}
	
	@Path("/menu")
	public void menu(){
		SimpleDateFormat df = new SimpleDateFormat("EEEEEEE 'às' HH:mm");
		result.include( "nextClass", df.format( studioScheduleDAO.getNextClass( session.getCustomer().getCustomerID())));
	}
	
}
