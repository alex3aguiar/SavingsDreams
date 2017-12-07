package controller;

import java.time.LocalDate;
import java.util.List;

import model.Movimentacao;
import model.Sonho;
import model.TipoMovimentacao;
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
		sonho.setDataRealizacao(LocalDate.now());
		
		MovimentacaoController movimentacaoController = new MovimentacaoController();
		Movimentacao movimentacao = new Movimentacao();

		movimentacao.setDescricao("Realizacao sonho" + sonho.getDescricao());
		movimentacao.setValor(sonho.getValor());
		movimentacao.setTipo(TipoMovimentacao.REALIZACAO_SONHO);
		
		movimentacaoController.salvar(movimentacao);
		sonho = this.alterar(sonho);
		
		return sonho;
	}



}
