package view.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import controller.PoupancaController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Poupanca;

public class Home extends Application {

	public static void main(String[] args) {
		PoupancaController poupancaController = new PoupancaController();
		if(poupancaController.buscarUltimo() == null) {
			poupancaController.salvar(new Poupanca(0, new BigDecimal(0.00), LocalDateTime.now()));
		}
		
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		abrirHome(primaryStage);
	}

	private void abrirHome(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(HomeFXController.getLocale());
			BorderPane borderPane = loader.load();

			Scene scene = new Scene(borderPane);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
