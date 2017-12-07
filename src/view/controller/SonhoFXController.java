package view.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import controller.MovimentacaoController;
import controller.PoupancaController;
import controller.SonhoController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;
import model.Movimentacao;
import model.Poupanca;
import model.Sonho;
import model.TipoMovimentacao;

public class SonhoFXController {

	@FXML
	private Button btnExcluirSonho;
	@FXML
	private Button btnRealizarSonho;
	@FXML
	private Label lblDataPrevisao;
	@FXML
	private Label lblDataRealizacao;
	@FXML
	private Label lblDataCriacao;
	@FXML
	private Label lblDescricao;
	@FXML
	private Label lblRotuloProgressBar;
	@FXML
	private Label lblValor;
	@FXML
	private ProgressBar progressBar;
	@FXML
	private BorderPane painel;

	Sonho sonho = null;
	
	FXMLLoader fxmlLoader = null;

	public SonhoFXController() {
		fxmlLoader = new FXMLLoader(getClass().getResource("/view/fxml/Sonho.fxml"));
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@FXML
	private void btnRealizarSonhoAction(ActionEvent event) {
		SonhoController controller = new SonhoController();

		Node source = (Node) event.getSource();
	    Window theStage = source.getScene().getWindow();
	    
	    Node lblSaldo = (Node) theStage.getScene().lookup("#lblSaldo");
	    
	    
		
		sonho.setRealizado(Boolean.TRUE);
		sonho.setDataRealizacao(LocalDate.now());
		
		MovimentacaoController movimentacaoController = new MovimentacaoController();
		Movimentacao movimentacao = new Movimentacao();
		
		movimentacao.setDescricao("Realizacao sonho" + sonho.getDescricao());
		movimentacao.setValor(sonho.getValor());
		movimentacao.setTipo(TipoMovimentacao.REALIZACAO_SONHO);
		
		movimentacaoController.salvar(movimentacao);
		controller.alterar(sonho);

	}

	@FXML
	private void btnExcluirSonhoAction(ActionEvent event) {
		SonhoController controller = new SonhoController();
		controller.deletar(sonho);
	}

	public void setSonho(Sonho sonho) {
		this.sonho = sonho;
		fillLayout();
	}

	public BorderPane getLayout() {
		return painel;
	}

	private void fillLayout() {
		if (sonho != null) {
			if (sonho.getRealizado().equals(Boolean.TRUE)) {
				btnExcluirSonho.setVisible(false);
				btnRealizarSonho.setVisible(false);
			}
			
			BigDecimal valorProgressBar = calcularProgressBar();

			NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
 
			lblDescricao.setText(sonho.getDescricao());
			lblValor.setText(numberFormat.format(sonho.getValor()));
			lblDataPrevisao.setText(sonho.getDataPrevisao().format(dtf).toString());
			lblDataRealizacao.setText(sonho.getDataRealizacao() != null ? sonho.getDataRealizacao().format(dtf).toString() : "");
			lblDataCriacao.setText(sonho.getDataCriacao().format(dtf).toString());
			progressBar.setProgress(valorProgressBar.doubleValue());
			
			if(sonho.getDataRealizacao() == null) {
				if(valorProgressBar.doubleValue() >= 1) {
					lblRotuloProgressBar.setText("Parabéns, você já possui dinheiro suficiente para realizar esse sonho");
				} else {
					lblRotuloProgressBar.setText("Você possui " + valorProgressBar.multiply(new BigDecimal(100)).doubleValue() + "% do dinheiro necessário para realizar esse sonho");
				}
				
			} else {
				progressBar.setVisible(false);
				lblRotuloProgressBar.setVisible(false);
			}

		}

	}
	
	public BigDecimal calcularProgressBar() {
		PoupancaController poupancaController = new PoupancaController();
		
		Poupanca poupanca = poupancaController.buscarPorId(sonho.getPoupanca().getId());
		sonho.setPoupanca(poupanca);
		BigDecimal porcentagem = null;
		try {
			porcentagem = (poupanca.getSaldo().divide(sonho.getValor(), RoundingMode.HALF_UP));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return porcentagem;
	}
	
	

}
