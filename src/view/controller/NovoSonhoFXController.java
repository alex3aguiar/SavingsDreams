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
	private Label lblError;
	
	
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
			
			if(sonhoController.salvar(sonho) == null) {
				lblError.setText("Erro ao salvar no banco");
			} else {
				limparForm();
				lblError.setText("Salvo com sucesso");
				Thread.sleep(10 * 1000);
				lblError.setText(null);
				
			}
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
		lblError.setText(null);
	}
	
	
	public Boolean validarCampos() {
		
		if(StringUtils.isBlank(txtDescricao.getText()) || StringUtils.isBlank(txtValor.getText()) || txtDataPrevisao.getValue() == null){
			lblError.setText("Preencha todos os campos");
			return false;
		}
			
		if(txtDataPrevisao.getValue().isBefore(LocalDate.now())) {
			lblError.setText("A data não pode ser posterior ao dia de hoje");
			return false;
		} 
		
		if(!validarNumero(txtValor.getText())){
			lblError.setText("Valor inválido");
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
