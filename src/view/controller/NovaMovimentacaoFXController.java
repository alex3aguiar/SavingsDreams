package view.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;

import org.apache.commons.lang3.StringUtils;

import controller.MovimentacaoController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.Movimentacao;
import model.TipoMovimentacao;

public class NovaMovimentacaoFXController {
	@FXML
	TextField txtDescricao;
	@FXML
	TextField txtValor;
	@FXML
	RadioButton rbEntrada;
	@FXML
	RadioButton rbSaida;
	@FXML
	Button btnSalvar;
	@FXML 
	Button btnLimpar;
	@FXML
	Label lblError;
	
	public static URL getLocale() {
		return NovaMovimentacaoFXController.class.getResource("/view/fxml/NovaMovimentacao.fxml");
	}
	
	@FXML
	public void btnSalvarAction(ActionEvent event) throws ParseException, InterruptedException{
		if(validarCampos()){
			DecimalFormat df = new DecimalFormat("0.##");
			df.setParseBigDecimal(true);
			
			MovimentacaoController movimentacaoController = new MovimentacaoController();
			Movimentacao movimentacao = new Movimentacao();
			
			movimentacao.setDescricao(txtDescricao.getText());
			movimentacao.setValor((BigDecimal) df.parse(txtValor.getText()));
			movimentacao.setTipo(rbEntrada.isSelected() ? TipoMovimentacao.ENTRADA:TipoMovimentacao.SAIDA);
			
			btnSalvar.setText("Salvando...");
			btnSalvar.setDisable(true);
			btnLimpar.setDisable(true);
			
			if(movimentacaoController.salvar(movimentacao) == null) {
				lblError.setText("Erro ao salvar no banco");
			} else {
				lblError.setText("Salvo com sucesso");
				limparForm();
				Thread.sleep(10 * 1000);
				lblError.setText(null);
			}
			
			btnSalvar.setText("Salvar");
			btnSalvar.setDisable(false);
			btnLimpar.setDisable(false);
			
		}
	}
	
	@FXML 
	public void btnLimparAction(ActionEvent event) {
		limparForm();
	}
	
	public void limparForm() {
		
		txtDescricao.clear();
		rbEntrada.setSelected(true);
		txtValor.clear();
		lblError.setText(null);
	}
	
	
	public Boolean validarCampos() {
		if(StringUtils.isBlank(txtDescricao.getText()) || StringUtils.isBlank(txtValor.getText()) || (!rbEntrada.isSelected() && !rbSaida.isSelected())){
			lblError.setText("Preencha todos os campos");
			return false;
		} else if(!validarNumero(txtValor.getText())) {
			lblError.setText("Valor inválido");
			return false;
		} else {
			return true;
		}
	}
	
	public Boolean validarNumero(String valor) {
		try{
			DecimalFormat df = new DecimalFormat("0.##");
			df.setParseBigDecimal(true);
			df.parse(txtValor.getText());
			return true;
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	}

}
