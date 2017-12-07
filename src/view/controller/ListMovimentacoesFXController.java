package view.controller;

import java.net.URL;
import java.util.List;

import controller.MovimentacaoController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.util.Callback;
import model.Movimentacao;

public class ListMovimentacoesFXController {

	@FXML
	private ScrollPane painelScrollMovimentacoes;
	@FXML
	private ListView<Movimentacao> painelMovimentacoes;
	
	private List<Movimentacao> movimentacoes = null;

	private ObservableList<Movimentacao> observableList = FXCollections.observableArrayList();
	
	@FXML
	public void initialize() {
		setListView();
	}

	public static URL getLocale() {
		return ListMovimentacoesFXController.class.getResource("/view/fxml/ListMovimentacoes.fxml");
	}

	public void setListView() {
		MovimentacaoController movimentacaoController = new MovimentacaoController();
		movimentacoes = movimentacaoController.listar();
		observableList.setAll(movimentacoes);
		painelMovimentacoes.setItems(observableList);
		painelMovimentacoes.setCellFactory(new Callback<ListView<Movimentacao>, ListCell<Movimentacao>>() {
			@Override
			public ListCell<Movimentacao> call(ListView<Movimentacao> param) {
				return new ListMovimentacoesViewCell();
			}
		});
	}

}
