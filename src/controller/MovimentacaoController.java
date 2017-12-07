package controller;

import java.util.List;

import model.Movimentacao;
import model.Poupanca;
import model.Movimentacao;
import persistence.MovimentacaoDAO;
import persistence.MovimentacaoDAO;

public class MovimentacaoController extends Controller<Movimentacao> {

	@Override
	public Movimentacao salvar(Movimentacao movimentacao) {
		dao = new MovimentacaoDAO();
		PoupancaController poupancaController = new PoupancaController();
		Poupanca poupanca = poupancaController.buscarUltimo();
		
		poupancaController.atualizarValor(poupanca, movimentacao);
		
		movimentacao.setPoupanca(poupanca);
		movimentacao = dao.salvar(movimentacao);
		poupanca = poupancaController.alterar(poupanca);
		
		return movimentacao;
	}

	@Override
	public Movimentacao alterar(Movimentacao t) {
		return null;
	}

	@Override
	public Boolean deletar(Movimentacao t) {
		return null;
	}

	@Override
	public List<Movimentacao> listar() {
		dao = new MovimentacaoDAO();
		List<Movimentacao> movimentacoes = dao.getLista();
		
		return movimentacoes;
	}

	@Override
	public Movimentacao buscarPorId(Integer id) {
		return null;
	}

	

	
}
