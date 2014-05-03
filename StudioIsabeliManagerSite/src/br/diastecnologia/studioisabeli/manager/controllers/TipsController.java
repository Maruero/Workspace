package br.diastecnologia.studioisabeli.manager.controllers;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.diastecnologia.studioisabeli.beans.Tip;
import br.diastecnologia.studioisabeli.controllers.Controller;
import br.diastecnologia.studioisabeli.daos.TipDAO;
import br.diastecnologia.studioisabeli.manager.session.ManagerSession;

@Resource
public class TipsController extends Controller{

	private TipDAO tipDao;
	private ManagerSession session;
	
	public TipsController(ManagerSession _session, HttpServletResponse _response, Result _result, ServletContext _context, TipDAO _tipDao) {
		super(_response, _result, _context);
		this.tipDao = _tipDao;
		this.session = _session;
	}
	
	@Path("/dicas")
	public void tips(){
		List<Tip> tips = tipDao.getTips();
		result.include( "tips" , tips );
	}
	
	@Path("/adicionar-dica")
	public void addTip(){
		
	}
	
	@Path("/editar-dica")
	public void editTip( Integer tipID ){
		Tip tip = tipDao.getTip(tipID);
		result.include( "tip" , tip );
		result.forwardTo( TipsController.class ).addTip();
	}
	
	@Path("/salvar-dica")
	public void saveTip( Tip tip ){
		if( tip.getTipID() == null || tip.getTipID() == 0){
			tipDao.addTip(tip.getTitle(), tip.getText() );
		}else{
			tipDao.updateTip(tip);
		}
		session.setMessage( "Dica salva com sucesso.");
		result.redirectTo( TipsController.class).tips();
	}
	
	@Path("/remover-dica")
	public void removeTip( Integer tipID ){
		tipDao.removeTip(tipID);
		session.setMessage( "Dica removida com sucesso.");
		result.redirectTo( TipsController.class).tips();
	}

}
