package view.controller;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;

import controller.SonhoController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import model.Movimentacao;
import model.Sonho;

public class MovimentacaoFXController {

	@FXML
	private Label lblValor;
	@FXML
	private Label lblTipo;
	@FXML
	private Label lblData;
	@FXML
	private Label lblSonho;
	@FXML
	private Label lblDescricao;
	@FXML
	private BorderPane painel;

	Movimentacao movimentacao = null;
	
	FXMLLoader fxmlLoader = null;

	public MovimentacaoFXController() {
		fxmlLoader = new FXMLLoader(getClass().getResource("/view/fxml/Movimentacao.fxml"));
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
		fillLayout();
	}

	public BorderPane getLayout() {
		return painel;
	}

	private void fillLayout() {
		if (movimentacao != null) {
			NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
 
			lblDescricao.setText(movimentacao.getDescricao());
			lblValor.setText(numberFormat.format(movimentacao.getValor()));
			lblData.setText(movimentacao.getUltimaAtualizacao().format(dtf));
			lblTipo.setText(movimentacao.getTipo().getDescricao());
			
			if(movimentacao.getSonho().getId() > 0) {
				SonhoController sonhoController = new SonhoController();
				Sonho sonho = new Sonho();
				
				sonho = sonhoController.buscarPorId(movimentacao.getSonho().getId());
				
				lblSonho.setText(sonho.getDescricao());
			}

		}

	}

}
