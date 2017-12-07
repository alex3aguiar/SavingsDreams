package view.controller;

import java.net.URL;
import java.util.List;

import controller.SonhoController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.util.Callback;
import model.Sonho;

public class ListSonhosFXController {

	@FXML
	private ScrollPane painelScrollSonhos;
	@FXML
	private ListView<Sonho> painelSonhos;
	
	private List<Sonho> sonhos = null;

	private ObservableList<Sonho> observableList = FXCollections.observableArrayList();
	
	@FXML
	public void initialize() {
		setListView();
	}

	public static URL getLocale() {
		return ListSonhosFXController.class.getResource("/view/fxml/ListSonhos.fxml");
	}

	public void setListView() {
		SonhoController sonhoController = new SonhoController();
		sonhos = sonhoController.listar();
		observableList.setAll(sonhos);
		painelSonhos.setItems(observableList);
		painelSonhos.setCellFactory(new Callback<ListView<Sonho>, ListCell<Sonho>>() {
			@Override
			public ListCell<Sonho> call(ListView<Sonho> param) {
				return new ListSonhosViewCell();
			}
		});
	}

}
