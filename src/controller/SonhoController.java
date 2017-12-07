package controller;

import java.util.List;

import model.Sonho;
import persistence.SonhoDAO;

public class SonhoController extends Controller<Sonho> {
	
	@Override
	public Sonho salvar(Sonho sonho) {
		dao = new SonhoDAO();
		PoupancaController poupancaController = new PoupancaController();
		
		sonho.setPoupanca(poupancaController.buscarUltimo());
		sonho = dao.salvar(sonho);
		
		return sonho;
	}

	@Override
	public Sonho alterar(Sonho sonho) {
		dao = new SonhoDAO();
		dao.alterar(sonho);
		
		return sonho;
	}

	@Override
	public Boolean deletar(Sonho sonho) {
		dao = new SonhoDAO();
		
		return dao.deletar(sonho);
	}
	
	@Override
	public List<Sonho> listar() {
		dao = new SonhoDAO();
		List<Sonho> sonhos = dao.getLista();
		
		return sonhos;
	}

	@Override
	public Sonho buscarPorId(Integer id) {
		dao = new SonhoDAO();
		Sonho sonho = dao.getById(id);
		
		return sonho;
	}
	
	public Sonho marcarComoRealizado(Sonho sonho) {
		sonho.setRealizado(Boolean.TRUE);
		sonho = this.salvar(sonho);
		
		return sonho;
	}



}
