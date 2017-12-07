package view.controller;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import model.Poupanca;
import persistence.DAO;
import persistence.PoupancaDAO;

public class HomeFXController {

	@FXML
	private BorderPane painelPrincipal;
	@FXML
	private Pane rightFrame;
	@FXML
	private Pane bottomFrame;
	@FXML
	private Pane leftFrame;
	@FXML
	private BorderPane painelMenu;
	@FXML
	private Label lblSaldo;
	@FXML
	private Button btnHome;
	@FXML
	private Button btNovoSonho;
	@FXML
	private Button btnNovaMovimentacao;
	@FXML
	private Button btnFecharJanela;
	@FXML
	private Button btnMovimentacoes;

	@FXML
	public void initialize() {
		atualizarSaldo();
	}

	public static URL getLocale() {
		return HomeFXController.class.getResource("/view/fxml/Home.fxml");
	}

	@FXML
	private void btnFecharJanelaAction(ActionEvent event) {
		System.exit(0);
	}

	@FXML
	private void btnHomeAction(ActionEvent event) {
		carregarPainel(ListSonhosFXController.getLocale());
	}
	
	@FXML
	private void btnMovimentacoesAction(ActionEvent event) {
		carregarPainel(ListMovimentacoesFXController.getLocale());
	}

	@FXML
	private void btnNovoSonhoAction(ActionEvent event) {
		carregarPainel(NovoSonhoFXController.getLocale());
	}

	@FXML
	private void btnNovaMovimentacaoAction(ActionEvent event) {
		carregarPainel(NovaMovimentacaoFXController.getLocale());
	}

	// @FXML
	// private void btnPainelMenuMouseEntered(ActionEvent event) {
	// Button button = ((Button)event.getSource());
	// System.out.println("oiee " + button.getText());
	// }
	//
	// @FXML
	// private void btnPainelMenuMouseExited(ActionEvent event) {
	// Button button = ((Button)event.getSource());
	// System.out.println("oiee " + button.getText());
	// }

	private void carregarPainel(URL locale) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(locale);
			painelPrincipal.setCenter(loader.load());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void atualizarSaldo() {
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
		DAO<Poupanca> dao = new PoupancaDAO();
		lblSaldo.setText(numberFormat.format(dao.getLast().getSaldo()));
	}
}
