package view.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZonedDateTime;

import org.apache.commons.lang3.StringUtils;

import controller.SonhoController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Sonho;

public class NovoSonhoFXController {
	@FXML
	private TextField txtDescricao;
	@FXML
	private TextField txtValor;
	@FXML
	private DatePicker txtDataPrevisao;
	@FXML
	private Button btnSalvar;
	@FXML 
	private Button btnLimpar;
	@FXML
	private Label lblStatus;
	
	
	public static URL getLocale() {
		return NovoSonhoFXController.class.getResource("/view/fxml/NovoSonho.fxml");
	}
	
	@FXML
	public void btnSalvarAction(ActionEvent event) throws ParseException, InterruptedException{
		if(validarCampos()){
			DecimalFormat df = new DecimalFormat("0.##");
			df.setParseBigDecimal(true);
			
			SonhoController sonhoController = new SonhoController();
			Sonho sonho = new Sonho();
			
			sonho.setDescricao(txtDescricao.getText());
			sonho.setValor((BigDecimal) df.parse(txtValor.getText()));
			sonho.setDataPrevisao(txtDataPrevisao.getValue());
			
			lblStatus.setText("Salvando...");
			btnSalvar.setDisable(true);
			btnLimpar.setDisable(true);
			
			if(sonhoController.salvar(sonho) == null) {
				lblStatus.setText("Erro ao salvar no banco");
			} else {
				limparForm();
				lblStatus.setText("Salvo com sucesso");
			}
			
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
		txtDataPrevisao.setValue(LocalDate.now());
		txtValor.clear();
		lblStatus.setText(null);
	}
	
	
	public Boolean validarCampos() {
		
		if(StringUtils.isBlank(txtDescricao.getText()) || StringUtils.isBlank(txtValor.getText()) || txtDataPrevisao.getValue() == null){
			lblStatus.setText("Preencha todos os campos");
			return false;
		}
			
		if(txtDataPrevisao.getValue().isBefore(LocalDate.now())) {
			lblStatus.setText("A data não pode ser posterior ao dia de hoje");
			return false;
		} 
		
		if(!validarNumero(txtValor.getText())){
			lblStatus.setText("Valor inválido");
			return false;
		} 
			return true;
	}
	
	public Boolean validarNumero(String valor) {
		try{
			DecimalFormat df = new DecimalFormat("0.##");
			df.setParseBigDecimal(true);
			BigDecimal bigDecimal = (BigDecimal) df.parse(txtValor.getText());
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
}
