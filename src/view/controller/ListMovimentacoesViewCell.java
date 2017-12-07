package view.controller;

import javafx.scene.control.ListCell;
import model.Movimentacao;

public class ListMovimentacoesViewCell extends ListCell<Movimentacao> {

	@Override
	public void updateItem(Movimentacao movimentacao, boolean empty) {
		super.updateItem(movimentacao, empty);
		if (movimentacao != null) {
			MovimentacaoFXController movimentacaoFXController = new MovimentacaoFXController();
			movimentacaoFXController.setMovimentacao(movimentacao);
			setGraphic(movimentacaoFXController.getLayout());
		}
	}
}
